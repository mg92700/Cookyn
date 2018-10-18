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

import com.general.dao.FavorisDao;
import com.general.dao.RecetteDao;
import com.general.dao.RelationDao;
import com.general.dao.UserDao;
import com.general.dto.UserDto;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;
import com.general.service.Status;

@Controller
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	Status status;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RecetteDao recetteDao;
	
	@Autowired
	FavorisDao favorisDao;
	
	@Autowired
	RelationDao relationDao;
	
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
	public UserDto UsersByUsername(String username)
	{
		User u = userDao.findByUsernameUser(username);
		UserDto userReturn = null;
		if(u!= null) {
			userReturn =(UserDto)JTransfo.convert(u);
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbFollower(relationDao.findAllByFriend(u).size());
			userReturn.setNbFollowing(relationDao.findAllByUser(u).size());
		}
		return userReturn;
	}
	
	
	@RequestMapping(value = "/getUserById/{idUser}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto getUserById(@PathVariable int idUser)
	{
		User u = userDao.findUserByIdUser(idUser);
		UserDto userReturn = null;
		if(u!= null) {
			userReturn =(UserDto)JTransfo.convert(u);
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbFollower(relationDao.findAllByFriend(u).size());
			userReturn.setNbFollowing(relationDao.findAllByUser(u).size());
		}
		return userReturn;
	}
	
	/*
	@RequestMapping(value = "/CreateOrUpdateUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto CreateUser(@RequestBody UserDto user)
	{
		UserDto userReturn = null;
		User u = null;
		if(user!=null)
		{
			if((userDao.findAllWhereNom(user.getNomUser()).size()==0 && userDao.findAllWhereMail(user.getMailUser()).size()==0))
			{
				user.setPasswordUser(cryptageService.encrypt(user.getPasswordUser()));			
				u = (User) JTransfo.convert(user);

			} else if((user.getIdUser() != null)) {
				if(cryptageService.encrypt(user.getPasswordUser()).equals(userDao.findUserByIdUser(user.getIdUser()).getPasswordUser())) {
					if(user.getNewPassword() != null) {
						user.setPasswordUser(cryptageService.encrypt(user.getNewPassword()));
					}else {
						user.setPasswordUser(cryptageService.encrypt(user.getPasswordUser()));						
					}
					u = (User) JTransfo.convert(user);
				}
			}
		}
		if(u!= null) {
			userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbFollower(relationDao.findAllByFriend(u).size());
			userReturn.setNbFollowing(relationDao.findAllByUser(u).size());
		}
		return userReturn;
	}*/

	
	
	

	@RequestMapping(value = "/UpdateUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto UpdateUser(@RequestBody UserDto user)
	{
		UserDto userReturn = null;
		User u = null;
		if(user!=null)
		{
			
			if(user.getNewPassword() != null) 
			{
				if(cryptageService.encrypt(user.getPasswordUser()).equals(userDao.findUserByIdUser(user.getIdUser()).getPasswordUser())) 				
						user.setPasswordUser(cryptageService.encrypt(user.getNewPassword()));
			}
			else
			{
				u=userDao.findUserByIdUser(user.getIdUser());
				user.setPasswordUser(u.getPasswordUser());
				
			}
			u = (User) JTransfo.convert(user);
		}
		if(u!= null) {
			userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbFollower(relationDao.findAllByFriend(u).size()); 
			userReturn.setNbFollowing(relationDao.findAllByUser(u).size());
		}
		return userReturn;
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto Login(@RequestBody UserDto user)
	{
		User u = null;
		UserDto userReturn = null;
		if( userDao.findByUsernameUser(user.getUsernameUser()) != null ){
			u = userDao.findByUsernameUser(user.getUsernameUser());
		}
		else if (userDao.findByMailUser(user.getMailUser()) != null ) {
			u = userDao.findByMailUser(user.getMailUser());
		}
		if(u!= null) {
			if(u.getPasswordUser().equals(cryptageService.encrypt(user.getPasswordUser()))) {
				userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
				userReturn.setPasswordUser(null);
				userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
				userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
				userReturn.setNbFollower(relationDao.findAllByFriend(u).size());
				userReturn.setNbFollowing(relationDao.findAllByUser(u).size());
			}
		}
		return userReturn;
	}
}
