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

import com.general.dao.IngredientDao;
import com.general.dao.PlanningDao;
import com.general.dao.UserDao;
import com.general.model.Ingredient;
import com.general.model.Planning;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/Planning")

public class PlanningController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	PlanningDao planningDao;
	
	@Autowired 
	CryptageService cryptageService;
	
	@RequestMapping(value = "/PlanningById", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Planning> listIngredientLibelle()
	{
		return null;
	}
	
	
	@RequestMapping(value = "/PlanningByUserId/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Planning> listCatIngredient(@PathVariable int id)
	{
		User user = new User();
		user.setIdUser(id);
		
		List<Planning> plannings = planningDao.findAllByuser(user);
		return plannings;
	}
	
	@RequestMapping(value = "/AddPlanning", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Planning AddIngredient(@RequestBody Planning planning)
	{
		
		if(planning!=null)
		{
			Planning thePlanning = planningDao.saveAndFlush(planning);
			return thePlanning;
		}
		else
			return null;
	}
	
	@RequestMapping(value = "/UpdatePlanning", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Planning UpdatePlanning(@RequestBody Planning pl)
	{
		pl=planningDao.saveAndFlush(pl);
		return pl;
	}
	
}
