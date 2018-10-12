package com.general.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.UniteDao;
import com.general.dao.UserDao;
import com.general.model.Ingredient;
import com.general.model.Unite;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;
import com.general.service.Status;

@Controller
@RestController
@RequestMapping(value = "/unite")

public class UniteController {
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	Status status;
	
	@Autowired
	UniteDao uniteDao;
	
	@Autowired 
	CryptageService cryptageService;
	
	@RequestMapping(value = "/listUnite", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Unite> listUnite()
	{
		List<Unite> unite = uniteDao.findAll();
		return unite;
	}
	
	@RequestMapping(value = "/Unitelibelle", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Unite> listUniteLibelle()
	{
		List<Unite> unite = uniteDao.findAllWhereNom("gr");
		return unite;
	}
	
	
	@RequestMapping(value = "/AddUnite", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Unite AddUnite(@RequestBody Unite ig)
	{
		
		if(ig!=null)
		{
			Unite addig = uniteDao.saveAndFlush(ig);
			return addig;
		}
		else
			return null;
	}
	
	
	@RequestMapping(value = "/UpdateUnite", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Unite UpdateUnite(@RequestBody Unite uni)
	{
		uni=uniteDao.saveAndFlush(uni);
		return uni;
	}
	
	
	
}
