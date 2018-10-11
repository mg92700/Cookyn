package com.general.controller;
import java.util.ArrayList;
import java.util.List;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.dao.EtapeDao;
import com.general.dao.RecetteDao;
import com.general.dao.RecetteIngredientDao;
import com.general.dto.RecetteDto;
import com.general.model.Etape;
import com.general.model.Ingredient;
import com.general.model.Note;
import com.general.model.Recette;
import com.general.model.RecetteIngredient;
import com.general.model.User;
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
	EtapeDao etapeDao;
	@Autowired
	RecetteDao recetteDao;
	@Autowired
	RecetteIngredientDao recetteIngredientDao;
	
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
	@RequestMapping(value = "/ByIdRecetteAll", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public RecetteDto RcetteByIdAll(int idRecette)
	{
		Recette r= recetteDao.findByidRecette(idRecette);
		List<Etape> etapes=etapeDao.findAllByrecette(r);
		List<RecetteIngredient> ri=recetteIngredientDao.findAllByrecette(r);
		List<Ingredient> ingredients=new ArrayList<Ingredient>();
		for (RecetteIngredient recetteIngredient : ri) {
			ingredients.add(recetteIngredient.getIngredient());
		}
		RecetteDto recette=new RecetteDto();
		recette.setRecette(r);
		recette.setIngredients(ingredients);
		recette.setEtapes(etapes);
		return recette;
	}
	
	
	@RequestMapping(value = "/AddRecette", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Recette AddRecette(@RequestBody Recette ir)
	{
		
		if(ir!=null)
		{
			Recette addir = recetteDao.saveAndFlush(ir);
			return addir;
		}
		else
			return null;
	}
	
	
	@RequestMapping(value = "/UpdateRecette", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Recette UpdateNote(@RequestBody Recette re)
	{

		re=recetteDao.saveAndFlush(re);
		return re;
	}
	

}
