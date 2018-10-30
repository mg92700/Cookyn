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

import com.general.dao.EtapeDao;
import com.general.dao.IngredientDao;
import com.general.dao.RecetteDao;
import com.general.dao.RecetteIngredientDao;
import com.general.dao.UniteDao;
import com.general.dao.UserDao;
import com.general.dto.RecetteDto;
import com.general.model.Etape;
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
	IngredientDao ingredientDao;
	@Autowired
	UserDao userDao;
	@Autowired
	UniteDao uniteDao;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/GetListRecette", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> GetListRecette()
	{
		List<Recette> recettes = recetteDao.findAll();
		return recettes;
	}
	
	@RequestMapping(value = "/GetListLibelleRecette/{libelleRecette}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> GetListLibelleRecette(@PathVariable String libelleRecette)
	{
		List<Recette> recettes = recetteDao.findAllWhereNom(libelleRecette);
		return recettes;
	}
	
	@RequestMapping(value = "/ByIdRecetteAll/{idRecette}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public RecetteDto RcetteByIdAll(@PathVariable int idRecette)
	{
		Recette r= recetteDao.findByIdRecette(idRecette);
		List<Etape> etapes=etapeDao.findAllByrecette(r);
		List<RecetteIngredient> ri=recetteIngredientDao.findAllByrecette(r);
//		List<Ingredient> ingredients=new ArrayList<Ingredient>();
//		for (RecetteIngredient recetteIngredient : ri) {
//			ingredients.add(recetteIngredient.getIngredient());
//		}
		RecetteDto recette=new RecetteDto();
		recette.setRecette(r);
		recette.setIngredients(ri);
		recette.setEtapes(etapes);
		return recette;
	}
	
	
	@RequestMapping(value = "/AddRecette", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public RecetteDto AddRecette(@RequestBody RecetteDto rec)
	{
		RecetteDto recDto = new RecetteDto();
		List<Etape> etapes = new ArrayList<>();
		List<RecetteIngredient> ingredients = new ArrayList<>();
		User u = new User();
		if(rec!=null)
		{
			u = userDao.findUserByIdUser(rec.getRecette().getUser().getIdUser());
			rec.getRecette().setUser(u);
			recDto.setRecette(recetteDao.saveAndFlush(rec.getRecette()));
			if(rec.getEtapes()!= null) {				
				for(int i=0; i<rec.getEtapes().size(); i++) {
					rec.getEtapes().get(i).setRecette(rec.getRecette());
					etapes.add(etapeDao.saveAndFlush(rec.getEtapes().get(i)));
				}
				recDto.setEtapes(etapes);
			}
			if(rec.getIngredients() != null) {		
				for(int i=0; i< rec.getIngredients().size(); i++) {
					rec.getIngredients().get(i).setRecette(rec.getRecette());
					recetteIngredientDao.saveAndFlush(rec.getIngredients().get(i));
					ingredients.add(rec.getIngredients().get(i));
				}
				recDto.setIngredients(ingredients);
			}
			return recDto;
		}
		else
			return null;
	}
	
	
	@RequestMapping(value = "/UpdateRecette", method = RequestMethod.PUT,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Recette UpdateNote(@RequestBody Recette rec)
	{
		rec=recetteDao.saveAndFlush(rec);
		return rec;
	}
	
	@RequestMapping(value = "/GetListRecetteByFiltre/{filtre}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> GetListRecetteByFiltre(@PathVariable String filtre)
	{

		List<Recette> rec=recetteDao.findAllByFiltre(filtre);
		return rec;
	}

}