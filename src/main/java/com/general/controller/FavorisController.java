package com.general.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.FavorisDao;
import com.general.dao.UserDao;
import com.general.model.Favoris;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/Favoris")
public class FavorisController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	FavorisDao favorisDao;
	@Autowired
	UserDao userDao;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/listFavorisByUser/{idUser}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Favoris> listFavorisByUser(@PathVariable int idUser)
	{
		User user=new User();
		user.setIdUser(idUser);
		List<Favoris> favoris = favorisDao.findAllByuser(user);
		return favoris;
	}
	
	@RequestMapping(value = "/AddFavoris", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Favoris AddFavoris(@RequestBody Favoris favoris)
	{
		favorisDao.saveAndFlush(favoris);
		return favoris;
	}
	
	@RequestMapping(value = "/UpdateFavoris", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Favoris UpdateFavoris(@RequestBody Favoris favoris)
	{
		favorisDao.saveAndFlush(favoris);
		return favoris;
	}
	
}