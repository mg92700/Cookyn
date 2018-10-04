package com.general.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.UserDao;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/api")
public class GeneralController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/listUsers", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> listUsers()
	{
		List<User> users = userDao.findAll();
		return users;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public String test()
	{
		return apiService.firstFonction();
	}
}
