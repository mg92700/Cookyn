package com.general.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.UserDao;
import com.general.model.User;

@Controller
@RestController
@RequestMapping(value = "/userStatistique")
public class UserStatistiqueController {
	
	
	@Autowired
	UserDao userDao;
	
	///renvoie les users cree par date choisie par rapport à la date d'aujourd'hui
	@RequestMapping(value = "/GetStatistiqueUserByDate/{date}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetlistUsersByOffSet(@PathVariable String date)
	{
		
		Map<String, Object> map = new HashMap<>(); 
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date dateParam = null;
		try {
			 dateParam=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dateNow = new Date();
		
		
		List<User> lstUser= userDao.findAllUserByDate(dateNow, dateParam);
		map.put("UserCountByDate", lstUser.size());
		
		return map;
		
		
	}
	
	///renvoie les users connecter
	@RequestMapping(value = "/GetStatistiqueUserByDate", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetNombreUserConnecter()
	{
		
		Map<String, Object> map = new HashMap<>(); 
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date dateParam = null;
	
		
		
		List<User> lstUser= userDao.findAllUserConnecte();
		map.put("UserCountByDate", lstUser.size());
		
		return map;
		
		
	}
	
	
	
	
	


}
