package com.general.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.IngredientDao;
import com.general.dao.RecetteDao;
import com.general.dao.UserDao;
import com.general.dto.UserDto;
import com.general.model.Ingredient;
import com.general.model.Recette;
import com.general.model.User;

import com.general.security.TokenSecurity;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	
	@Autowired
	RecetteDao recetteDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	CryptageService cryptageService;
	
	@Autowired
	TokenSecurity t;
    
    @Autowired
    IngredientDao ingredientDao;
    
	
	@RequestMapping(value = "/LogAdmin", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> LogAdmin(@RequestBody UserDto user,HttpServletRequest request)
	{
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		
		String token=null;
        
      
		Boolean trouver=false;
		String mdpEncore=cryptageService.encrypt(user.getPasswordUser());
		User u= userDao.findByMailUser(user.getMailUser());
		if(u!=null)
		{
			if(u.getRole().equals("admin")) 
			{
				if(mdpEncore.equals(u.getPasswordUser()))
				{
					trouver=true;
					token=t.getToken();
					
				}
			}
		}
		
		mapReturn.put("EstConnecter", trouver);
		mapReturn.put("Token", token);
		
		return mapReturn;
		
	}

	@RequestMapping(value = "/GetListUsers", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> GetListUsers()
	{
		List<User> users = userDao.findAll();
		
		return users;
	}

	@RequestMapping(value = "/GetListRecette", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> GetListRecette()
	{
		List<Recette> recettes = recetteDao.findAll();
		return recettes;
	}
	
    @RequestMapping(value = "/GetListAllIngredient/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
    @CrossOrigin(origins = "*")
    public Map<String, Object> GetListAllIngredient(@PathVariable int offset)
    {
    	
    	 List<Ingredient> ingredients = ingredientDao.findAll();
    	 List<Ingredient> ingredientsSub = new ArrayList<>();
 		Map<String, Object> map = new HashMap<>(); 
 		//return recettes;
 		int limite=20;
 		
 		if (offset>0) 
 		{
 			
 	        if (offset >= ingredients.size()) 
 	        {
 	        	ingredientsSub= ingredients.subList(0, 0); //return empty.
 	        }
 	        if(offset>ingredients.size())
 	        {
 	        	map.put("offset", ingredients.size());
 	        	map.put("listIngredients", ingredientsSub);
 	        	map.put("limite", limite);
 	        	return map;
 	        	
 	        }
 	        if (2 >-1) 
 	        {
 	            //apply offset and limit
 	        	ingredientsSub= ingredients.subList(offset, Math.min(offset+limite, ingredients.size()));
 	        } 
 	        else 
 	        {
 	            //apply just offset
 	        	ingredientsSub= ingredients.subList(offset, ingredients.size());
 	        }
 	        
 	    } 
 		else if (2 >-1) 
 		{
 	        //apply just limit
 			ingredientsSub= ingredients.subList(0, Math.min(limite, ingredients.size()));
 	    } else 
 	    {
 	    	ingredientsSub= ingredients.subList(0, ingredients.size());
 	    }
 		map.put("listIngredients", ingredientsSub);
 		map.put("offset", offset);
 		map.put("limite", limite);
 		return map;
    }
}
