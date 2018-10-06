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

import com.general.dao.RecetteDao;
import com.general.model.Recette;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/recette")
public class RecetteController {
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	RecetteDao recetteDao;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/listRecette", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> listRecette()
	{
		List<Recette> recettes = recetteDao.findAll();
		return recettes;
	}
	
	@RequestMapping(value = "/LibelleRecette", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> listLibelleRecette(@RequestBody Recette recette)
	{
		List<Recette> recettes = recetteDao.findAllWhereNom(recette.getLibelleRecette());
		return recettes;
	}
	
	@RequestMapping(value = "/ByLibelleRecette", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> RecettesByLibelleRecette(@RequestBody Recette recette)
	{
		List<Recette> recettes = recetteDao.findBylibelleRecette(recette.getLibelleRecette());
		return recettes;
	}
	
	

}
