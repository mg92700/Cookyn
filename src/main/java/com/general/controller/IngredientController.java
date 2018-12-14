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

import com.general.dao.IngredientDao;
import com.general.model.Ingredient;
import com.general.model.Note;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/ingredient")

public class IngredientController {

    @Autowired
    ApiService apiService;
    
    @Autowired
    JTransfo JTransfo;
    
    @Autowired
    IngredientDao ingredientDao;
    
    @Autowired
    CryptageService cryptageService;
    
    @RequestMapping(value = "/GetlistIngredientLibelle/{name}/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
    @CrossOrigin(origins = "*")
    public Map<String, Object> GetlistIngredientLibelle(@PathVariable String name, @PathVariable int offset)
    {
        List<Ingredient> ingredients = ingredientDao.findAllWhereNom(name);
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
    
    @RequestMapping(value = "/GetListCatIngredient/{name}/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
    @CrossOrigin(origins = "*")
    public Map<String, Object> GetListCatIngredient(@PathVariable String name, @PathVariable int offset)
    {
        List<Ingredient> ingredients = ingredientDao.findAllWhereCat(name);
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
    
    @RequestMapping(value = "/AddIngredient", method = RequestMethod.POST,headers="Accept=application/json")
    @CrossOrigin(origins = "*")
    public Ingredient AddIngredient(@RequestBody Ingredient ig)
    {
        
        if(ig!=null)
        {
            Ingredient addig = ingredientDao.saveAndFlush(ig);
            return addig;
        }
        else
            return null;
    }
    
    @RequestMapping(value = "/UpdateIngredient", method = RequestMethod.PUT,headers="Accept=application/json")
    @CrossOrigin(origins = "*")
    public Ingredient UpdateIngredient(@RequestBody Ingredient ingredient)
    {
        ingredient=ingredientDao.saveAndFlush(ingredient);
        return ingredient;
    }
}