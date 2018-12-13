package com.general.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.UserDao;
import com.general.dto.UserDto;
import com.general.model.User;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	CryptageService cryptageService;
	
	@RequestMapping(value = "/LogAdmin", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Boolean LogAdmin(@RequestBody UserDto user,HttpServletRequest request)
	{
		//recupe les données du header
	    Map<String, String> map = new HashMap<String, String>();
	    Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        //recupere value depuis la clés
        String Token=map.get("authorization");
        
      
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
					
				}
			}
		}
		return trouver;
		
	}

	@RequestMapping(value = "/GetListUsers", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> GetListUsers()
	{
		List<User> users = userDao.findAll();
		
		return users;
	}
}
