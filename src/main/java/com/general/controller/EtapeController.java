package com.general.controller;

import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.EtapeDao;
import com.general.dao.RecetteDao;
import com.general.dao.RecetteIngredientDao;
import com.general.model.Etape;
import com.general.model.Recette;
import com.general.service.ApiService;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/Etape")
public class EtapeController {

	@Autowired
	ApiService apiService;
	
	@Autowired
	JTransfo JTransfo;
	
	@Autowired
	EtapeDao etapeDao;
	@Autowired
	RecetteDao recetteDao;
	@Autowired
	RecetteIngredientDao recetteIngredientDao;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/listEtapes/{idRecette}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Etape> ListEtapes(@RequestParam int idRecette)
	{
		Recette recette=new Recette();
		recette.setIdRecette(idRecette);
		List<Etape> etapes = etapeDao.findAllByrecette(recette);
		return etapes;
	}
	
	@RequestMapping(value = "/AddEtapes", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Etape> AddEtapes(@RequestBody List<Etape> etapes)
	{
		for (Etape etape : etapes) {
			etapeDao.saveAndFlush(etape);
		}
		return etapes;
	}
	
	@RequestMapping(value = "/UpdateEtape", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Etape UpdateEtape(@RequestBody Etape etape)
	{
			etapeDao.saveAndFlush(etape);
			return etape;
	}

}
