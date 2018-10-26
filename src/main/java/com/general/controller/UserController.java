package com.general.controller;
import java.util.ArrayList;
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
import com.general.dto.AmieDto;
import com.general.dto.UserDto;
import com.general.model.Relation;
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
	
	@RequestMapping(value = "/listUsersByUsername", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> listUsersByUsername(String username)
	{
		List<User> users = userDao.findAllWhereNom(username);
		return users;
	}
	
	
	
	@RequestMapping(value = "/UsersByUsername", method = RequestMethod.POST,headers="Accept=application/json")
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
					u = (User) JTransfo.convert(user);
					userReturn =(UserDto)JTransfo.convert(userDao.saveAndFlush(u));
					userReturn.setPasswordUser(null);
					userReturn.setNbRecetteCreate(recetteDao.findAllByUser(u).size());
					userReturn.setNbRecetteFav(favorisDao.findAllByUser(u).size());
					userReturn.setNbFollower(relationDao.findAllByFriend(u).size());
					userReturn.setNbFollowing(relationDao.findAllByUser(u).size());
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
		}else {
			userReturn.setErrortxt("User est inconnue");
			}
		return userReturn;
	}
	
	@RequestMapping(value = "/GetListAbonne/{idUser}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<AmieDto> GetListAbonne(@PathVariable Integer idUser)
	{
		
		List<AmieDto> listFriend = new ArrayList<AmieDto>();
		if(idUser!=null)
		{
			User userConnecter= userDao.findUserByIdUser(idUser);
			List<Relation> listR=relationDao.findAllByUser(userConnecter);
			for (int i=0; i< listR.size(); i++) {
				AmieDto friendDto= new AmieDto();
			    User userFriend=userDao.findUserByIdUser(listR.get(i).getFriend().getIdUser());
			    friendDto.setIdUser(userFriend.getIdUser());
			    friendDto.setNomUser(userFriend.getNomUser());
			    friendDto.setPrenomUser(userFriend.getPrenomUser());
			    listFriend.add(friendDto);
				}
		}
		return listFriend;
		
	}
	
	@RequestMapping(value = "/GetListAbonnement/{idUser}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<AmieDto> GetListAbonnement(@PathVariable Integer idUser)
	{
		
		List<AmieDto> listFriend = new ArrayList<AmieDto>();
		if(idUser!=null)
		{
			User userConnecter= userDao.findUserByIdUser(idUser);
			List<Relation> listR=relationDao.findAllByFriend(userConnecter);
			for (int i=0; i< listR.size(); i++) {
				AmieDto friendDto= new AmieDto();
			    User userFriend=userDao.findUserByIdUser(listR.get(i).getUser().getIdUser());
			    friendDto.setIdUser(userFriend.getIdUser());
			    friendDto.setNomUser(userFriend.getNomUser());
			    friendDto.setPrenomUser(userFriend.getPrenomUser());
			    listFriend.add(friendDto);
				}
		}
		return listFriend;
		
	}
	
	
	@RequestMapping(value = "/GetListUserByFiltre/{filtre}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<User> GetListUserByFiltre(@PathVariable String filtre)
	{

		List<User> rec=userDao.findAllByFiltre(filtre);
		return rec;
	}
}
