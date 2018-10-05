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

import com.general.dao.UserDao;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
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
	
	@RequestMapping(value = "/Usernom", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> listUsersNom()
	{
		List<User> users = userDao.findAllWhereNom("elberkaouinajib");
		return users;
	}
	
	@RequestMapping(value = "/Userusename", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> listUsersByUsername(String username)
	{
		List<User> users = userDao.findAllWhereNom(username);
		return users;
	}
	
	@RequestMapping(value = "/ByUsername", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public User UsersByUsername(String username)
	{
		User users = userDao.findByusernameUser(username);
		return users;
	}
	
	
	@RequestMapping(value = "/CreateUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public User CreateUser(@RequestBody User user)
	{
		User test=user;
		if(user!=null)
		{
			User createedUser = userDao.saveAndFlush(user);
			return createedUser;
		}
		else
			return null;
	}

}
