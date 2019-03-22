package com.general.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.model.Recette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.UserDao;
import com.general.model.User;

import com.general.dao.RecetteDao;

@Controller
@RestController
@RequestMapping(value = "/adminStatistique")
public class AdminStatistiqueController {
	
	
	@Autowired
	UserDao userDao;

    @Autowired
    RecetteDao recetteDao;
	
	//On fait une moyenne du nombre de recettes par user
	@RequestMapping(value = "/moyenneRecettesByUser", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public int moyenneRecettesByUser() {
		//On récupère d'abord le nombre total de recettes
        List<Recette> recettes = recetteDao.findAll();
        int nbRecettes = recettes.size();

		//Puis on récupère le nombre total d'utilisateurs
        List<User> users = userDao.findAll();
        int nbUsers = users.size();

		//On divise ensuite le nombre de recettes par le nombre d'utilisateurs
        int moyenne = nbRecettes / nbUsers;

		return moyenne;
	}
}
