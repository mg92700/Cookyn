package com.general.controller;
import java.util.Date;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.FavorisDao;
import com.general.dao.RecetteDao;
import com.general.dao.RelationDao;
import com.general.dao.UserDao;
import com.general.dto.AmieDto;
import com.general.dto.RelationDto;
import com.general.dto.UserDto;
import com.general.model.Recette;
import com.general.model.Relation;
import com.general.model.User;
import com.general.service.ApiService;
import com.general.service.CryptageService;
import com.general.service.EmailValidator;
import com.general.service.Status;
import com.google.api.Authentication;
import com.google.protobuf.Method;

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
	
	
	EmailValidator validator = new EmailValidator();

	private String token;

	@RequestMapping(value = "/GetlistUsersByOffSet/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetlistUsersByOffSet(@PathVariable int offset)
	{
		List<User> users = userDao.findAll();
		List<User> userSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= users.size()) 
	        {
	        	userSub= users.subList(0, 0); //return empty.
	        }
	        if(offset>users.size())
	        {
	        	map.put("offset", users.size());
	        	map.put("listUser", userSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	userSub= users.subList(offset, Math.min(offset+limite, users.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	userSub= users.subList(offset, users.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
	    	userSub= users.subList(0, Math.min(limite, users.size()));
	    } else 
	    {
	    	userSub= users.subList(0, users.size());
	    }
		map.put("listUser", userSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
		
		
	}
	
	@RequestMapping(value = "/GetListByNom", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> GetListByNom()
	{
		List<User> users = userDao.findAllWhereNom("elberkaouinajib");
		return users;
	}
	
	@RequestMapping(value = "GetListUsersByUsername", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> GetListUsersByUsername(String username)
	{
		List<User> users = userDao.findAllWhereNom(username);
		return users;
	}
	
	@RequestMapping(value = "/GetUsersByUserName", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto GetUsersByUserName(String username)
	{
		User u = userDao.findByUsernameUser(username);
		UserDto userReturn = null;
		if(u!= null) {
			userReturn =(UserDto)JTransfo.convert(u);
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbAbonnement(relationDao.findAllByUser(u).size());
			userReturn.setNbAbonnee(relationDao.findAllByFriend(u).size());
		}
		return userReturn;
	}
	
	@RequestMapping(value = "/GetUserById/{idUser}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto GetUserById(@PathVariable int idUser)
	{
		User u = userDao.findUserByIdUser(idUser);
		String format = "dd/MM/yy H:mm:ss"; 
		SimpleDateFormat formater = new SimpleDateFormat( format ); 
		System.out.println(formater.format(u.getDateCreation())); 
		System.out.println(formater.format(u.getDateModification())); 

		UserDto userReturn = new UserDto();
		if(u!= null) {
			userReturn =(UserDto)JTransfo.convert(u);
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbAbonnement(relationDao.findAllByUser(u).size() );
			userReturn.setNbAbonnee(   relationDao.findAllByFriend(u).size());
		}else {
		userReturn.setErrortxt("User est inconnue et nike ta race antoine cordialement");}
		return userReturn;
	}

	@RequestMapping(value = "/CreateUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto CreateUser(@RequestBody UserDto user)
	{
		UserDto userReturn = new UserDto();
		User u =  null;
		if(user!=null)
		{
			System.out.println(userDao.findAllByUsernameUser(user.getUsernameUser()).size());
			if(userDao.findAllByUsernameUser(user.getUsernameUser()).size()==0 && userDao.findAllWhereMail(user.getMailUser()).size()==0){
			
				if(user.getPasswordUser()!= null) {
					user.setPasswordUser(cryptageService.encrypt(user.getPasswordUser()));	
					user.setRole("user");
					
					//String format = "dd/MM/yy H:mm:ss"; 
					//SimpleDateFormat formater = new SimpleDateFormat( format ); 
					//user.setDateCreation(formater.format( new Date()));
					user.setDateCreation(new Date());
					user.setDateModification(new Date());
					u = (User) JTransfo.convert(user);
					
					userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
					userReturn.setPasswordUser(null);
					userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
					userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
					userReturn.setNbAbonnement(relationDao.findAllByFriend(u).size());
					userReturn.setNbAbonnee(relationDao.findAllByUser(u).size());
					
				}else {
					userReturn.setErrortxt("Veuillez renseigner un mot de passe");
				}
			} else {
				userReturn.setErrortxt("Le username ou/et l'email est déjà utilisé");
			}
					
		}
		return userReturn;
	}
	
	@RequestMapping(value = "/UpdateUser", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto UpdateUser(@RequestBody UserDto user)
	{
		UserDto userReturn = null;
		User u = null;
		User userDb = userDao.findUserByIdUser(user.getIdUser());
		
		
		if(user!=null)
		{
			
			if(user.getNewPassword() != null) 
			{
				if(cryptageService.encrypt(user.getPasswordUser()).equals(userDao.findUserByIdUser(user.getIdUser()).getPasswordUser())) 				
				{
					user.setPasswordUser(cryptageService.encrypt(user.getNewPassword()));
					u = (User) JTransfo.convert(user);
				}
				else {
					return userReturn;
				}
		
			}

			else {

				//u=userDao.findUserByIdUser(user.getIdUser());
				user.setPasswordUser(userDao.findUserByIdUser(user.getIdUser()).getPasswordUser());
				u = (User) JTransfo.convert(user);
				
			}
			//prenom
			if(user.getPrenomUser()==null){
				u.setPrenomUser(userDb.getPrenomUser());
			}
			else 
			{
				u.setPrenomUser(user.getPrenomUser());
			}
			//nom
			if(user.getNomUser()==null){
				u.setNomUser(userDb.getNomUser());
			}
			else 
			{
				u.setNomUser(user.getNomUser());
			}
			//ville
			if(user.getVilleUser()==null){
				u.setVilleUser(userDb.getVilleUser());
			}
			else 
			{
				u.setVilleUser(user.getVilleUser());
			}
			//mail
			if(user.getMailUser()==null){
				u.setMailUser(userDb.getMailUser());
			}
			else 
			{
				u.setMailUser(user.getMailUser());
			}
			//username
			if(user.getUsernameUser()==null){
				u.setUsernameUser(userDb.getUsernameUser());
			}
			else 
			{
				u.setUsernameUser(user.getUsernameUser());
			}
			
			u.setDateCreation(userDb.getDateCreation());
			
		}
		if(u!= null) {
			u.setDateModification(new Date());
			userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
			userReturn.setPasswordUser(null);
			userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
			userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
			userReturn.setNbAbonnement(relationDao.findAllByFriend(u).size()); 
			userReturn.setNbAbonnee(relationDao.findAllByUser(u).size());
			
		}
		return userReturn;
	}
	
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public UserDto Login(@RequestBody UserDto user)
	{
		User u = null;
		UserDto userReturn = new UserDto();
		
		if(validator.validateEmail(user.getUsernameUser()))
		{
			//si c'est un email
			 if (userDao.findByMailUser(user.getUsernameUser()) != null ) {
				 u = userDao.findByMailUser(user.getUsernameUser());
			 	}
		
			
			
		}else {
			//si c'est pas un email
			if( userDao.findByUsernameUser(user.getUsernameUser()) != null ){
						u = userDao.findByUsernameUser(user.getUsernameUser());
				}
		}
		
	
		
		if(u!= null) 
		{
			if(u.getPasswordUser().equals(cryptageService.encrypt(user.getPasswordUser()))) 
			{
				userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
				userReturn.setPasswordUser(null);
				userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
				userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
				userReturn.setNbAbonnement(relationDao.findAllByFriend(u).size());
				userReturn.setNbAbonnee(relationDao.findAllByUser(u).size());
			}
			else {
				
				userReturn.setErrortxt("Combinaison e-mail & mot de passe incorrect");
			}
		}
		else 
		{
			userReturn.setErrortxt("User est inconnue");
		}
		return userReturn;
	}

	@RequestMapping(value = "/GetListUserByFiltre/{filtre}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> GetListUserByFiltre(@PathVariable String filtre)
	{

		List<User> rec=userDao.findAllByFiltre(filtre);
		return rec;
	}
	
}
