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

import com.general.dao.IngredientDao;
import com.general.dao.UserDao;
import com.general.model.Ingredient;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/api")

public class IngredientController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	IngredientDao ingredientDao;
	
	@Autowired 
	CryptageService cryptageService;
	
	@RequestMapping(value = "/Ingredientlibelle", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Ingredient> listIngredientLibelle()
	{
		List<Ingredient> ingredients = ingredientDao.findAllWhereNom("pomme");
		return ingredients;
	}
	
	
	@RequestMapping(value = "/CatIngredient", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Ingredient> listCatIngredient()
	{
		List<Ingredient> ingredients = ingredientDao.findAllWhereCat("fruit");
		return ingredients;
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
	
	
	
}
