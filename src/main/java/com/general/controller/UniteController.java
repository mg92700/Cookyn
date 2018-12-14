package com.general.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.UniteDao;
import com.general.model.Recette;
import com.general.model.Unite;
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
	
	
	@RequestMapping(value = "/GetListUnites/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object>  GetListUnites(@PathVariable int offset)
	{
		List<Unite> unite = uniteDao.findAll();
		List<Unite> uniteSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= unite.size()) 
	        {
	        	uniteSub= unite.subList(0, 0); //return empty.
	        }
	        if(offset>unite.size())
	        {
	        	map.put("offset", unite.size());
	        	map.put("listUnite", uniteSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	uniteSub= unite.subList(offset, Math.min(offset+limite, unite.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	uniteSub= unite.subList(offset, unite.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			uniteSub= unite.subList(0, Math.min(limite, unite.size()));
	    } else 
	    {
	    	uniteSub= unite.subList(0, unite.size());
	    }
		map.put("listUnite", uniteSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
	}
	
	@RequestMapping(value = "/UniteByLibelle/{libelleUnite}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Unite UniteByLibelle(@PathVariable String libelleUnite)
	{
		Unite unite = uniteDao.findBylibelleUnite(libelleUnite);
		return unite;
	}
	
	@RequestMapping(value = "/UniteById/{idUnite}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Unite UniteById(@PathVariable int idUnite)
	{
		Unite unite = uniteDao.findByidUnite(idUnite);
		return unite;
	}
	
	@RequestMapping(value = "/AddUnite", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Unite AddUnite(@RequestBody Unite uneUniteParam)
	{
		
		if(uneUniteParam!=null)
		{
			Unite uneUnite = uniteDao.saveAndFlush(uneUniteParam);
			return uneUnite;
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
