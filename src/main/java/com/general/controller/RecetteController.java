
package com.general.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.general.service.ServiceImageFtp;

import java.util.stream.Stream;

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
	ServiceImageFtp serviceFtp;
	
	@Autowired 
	CryptageService cryptageService;

	@RequestMapping(value = "/GetListRecette", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public List<Recette> GetListRecette()
	{
		List<Recette> recettes = recetteDao.findAll();
		return recettes;
	}
	
	@RequestMapping(value = "/GetListRecetteByOffSet/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetListRecetteByOffSet(@PathVariable int offset)
	{
		List<Recette> recettes = recetteDao.findAll();
		List<Recette> recetteSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= recettes.size()) 
	        {
	        	recetteSub= recettes.subList(0, 0); //return empty.
	        }
	        if(offset>recettes.size())
	        {
	        	map.put("offset", recettes.size());
	        	map.put("listRecette", recetteSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	recetteSub= recettes.subList(offset, Math.min(offset+limite, recettes.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	recetteSub= recettes.subList(offset, recettes.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			recetteSub= recettes.subList(0, Math.min(limite, recettes.size()));
	    } else 
	    {
	    	recetteSub= recettes.subList(0, recettes.size());
	    }
		map.put("listRecette", recetteSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
		
	}
	
	@RequestMapping(value = "/GetListByRecette/{libelleRecette}/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetListByRecette(@PathVariable String libelleRecette, @PathVariable int offset)
	{
		List<Recette> recettes = recetteDao.findAllWhereNom(libelleRecette);
		List<Recette> recetteSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= recettes.size()) 
	        {
	        	recetteSub= recettes.subList(0, 0); //return empty.
	        }
	        if(offset>recettes.size())
	        {
	        	map.put("offset", recettes.size());
	        	map.put("listRecette", recetteSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	recetteSub= recettes.subList(offset, Math.min(offset+limite, recettes.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	recetteSub= recettes.subList(offset, recettes.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			recetteSub= recettes.subList(0, Math.min(limite, recettes.size()));
	    } else 
	    {
	    	recetteSub= recettes.subList(0, recettes.size());
	    }
		map.put("listRecette", recetteSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
	}
	
	@RequestMapping(value = "/GetRecetteById/{idRecette}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public RecetteDto GetRecetteById(@PathVariable int idRecette)
	{
		Recette r= recetteDao.findByIdRecette(idRecette);
		List<Etape> etapes=etapeDao.findAllByrecette(r);
		List<RecetteIngredient> ri=recetteIngredientDao.findAllByrecette(r);
		r.getUser().setPasswordUser(null);
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
	public RecetteDto AddRecette(@RequestBody RecetteDto rec) throws IOException 
	{
		RecetteDto recDto = new RecetteDto();
		List<Etape> etapes = new ArrayList<>();
		List<RecetteIngredient> ingredients = new ArrayList<>();
		User u = new User();
		Date d = new Date();
		if(rec!=null)
		{
			u = userDao.findUserByIdUser(rec.getRecette().getUser().getIdUser());
			rec.getRecette().setUser(u);
			rec.setDateCreation(d);
			if(rec.getImageRecette()!=null)
			{
				byte[] images = Base64.getDecoder().decode(rec.getImageRecette());
				
				String url=serviceFtp.resultat(u.getUsernameUser(),rec.getRecette().getLibelleRecette() ,images);
				
				rec.getRecette().setPhotoRecette(url);
			}
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
	
	@RequestMapping(value = "/GetListRecetteByFiltre/{filtre}/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetListRecetteByFiltre(@PathVariable String filtre, @PathVariable int offset)
	{

		List<Recette> recettes=recetteDao.findAllByFiltre(filtre);
		List<Recette> recetteSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= recettes.size()) 
	        {
	        	recetteSub= recettes.subList(0, 0); //return empty.
	        }
	        if(offset>recettes.size())
	        {
	        	map.put("offset", recettes.size());
	        	map.put("listRecette", recetteSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	recetteSub= recettes.subList(offset, Math.min(offset+limite, recettes.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	recetteSub= recettes.subList(offset, recettes.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			recetteSub= recettes.subList(0, Math.min(limite, recettes.size()));
	    } else 
	    {
	    	recetteSub= recettes.subList(0, recettes.size());
	    }
		map.put("listRecette", recetteSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
	}
	
// recette creer par user
	@RequestMapping(value = "/GetListRecetteCreatedByUser/{idU}/{offset}", method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GetListRecetteCreatedByUser(@PathVariable int idU,@PathVariable int offset)
	{
		User u = new User();
		u.setIdUser(idU);
		List<Recette> recettes=recetteDao.findAllByUser(u);
		//List<Recette> rec = new ArrayList<>();
		
		List<Recette> recetteSub = new ArrayList<>();
		Map<String, Object> map = new HashMap<>(); 
		//return recettes;
		int limite=20;
		
		if (offset>0) 
		{
			
	        if (offset >= recettes.size()) 
	        {
	        	recetteSub= recettes.subList(0, 0); //return empty.
	        }
	        if(offset>recettes.size())
	        {
	        	map.put("offset", recettes.size());
	        	map.put("listRecette", recetteSub);
	        	map.put("limite", limite);
	        	return map;
	        	
	        }
	        if (2 >-1) 
	        {
	            //apply offset and limit
	        	recetteSub= recettes.subList(offset, Math.min(offset+limite, recettes.size()));
	        } 
	        else 
	        {
	            //apply just offset
	        	recetteSub= recettes.subList(offset, recettes.size());
	        }
	        
	    } 
		else if (2 >-1) 
		{
	        //apply just limit
			recetteSub= recettes.subList(0, Math.min(limite, recettes.size()));
	    } else 
	    {
	    	recetteSub= recettes.subList(0, recettes.size());
	    }
		map.put("listRecette", recetteSub);
		map.put("offset", offset);
		map.put("limite", limite);
		return map;
	}
	
	
}
