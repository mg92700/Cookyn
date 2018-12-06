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

import com.general.dao.PlanningDao;
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
	
	
//	public List<Planning> createOffSet(){
//		
//	}
	
	
	@RequestMapping(value = "/GetListPlanningsByUserOffset/{idUser}/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetListPlanningsByUserOffset(@PathVariable int idUser, @PathVariable int offset)
	{
		User user =new User();
		user.setIdUser(idUser);
		List<Planning> planning=planningDao.findAllByuser(user);
		List<Planning> planningSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= planning.size()) 
	        {
	        	planningSub= planning.subList(0, 0); //return empty.
	        }
	        if(offset>planning.size())
	        {
	        	map.put("offset", planning.size());
	        	map.put("listPlanning", planningSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	planningSub= planning.subList(offset, Math.min(offset+limite, planning.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	planningSub= planning.subList(offset, planning.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			planningSub= planning.subList(0, Math.min(limite, planning.size()));
	    } else 
	    {
	    	planningSub= planning.subList(0, planning.size());
	    }
		map.put("listPlanning", planningSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
	}
	
	
	@RequestMapping(value = "/GetListPlanningsByOffset/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetListPlanningsByOffset(@PathVariable int offset)
	{
		List<Planning> planning = planningDao.findAll();
		List<Planning> planningSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= planning.size()) 
	        {
	        	planningSub= planning.subList(0, 0); //return empty.
	        }
	        if(offset>planning.size())
	        {
	        	map.put("offset", planning.size());
	        	map.put("listPlanning", planningSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	planningSub= planning.subList(offset, Math.min(offset+limite, planning.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	planningSub= planning.subList(offset, planning.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			planningSub= planning.subList(0, Math.min(limite, planning.size()));
	    } else 
	    {
	    	planningSub= planning.subList(0, planning.size());
	    }
		map.put("listPlanning", planningSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
		
		
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
