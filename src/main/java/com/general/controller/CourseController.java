package com.general.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.general.dao.EtapeDao;
import com.general.dao.FavorisDao;
import com.general.dao.IngredientDao;
import com.general.dao.NoteDao;
import com.general.dao.PlanningDao;
import com.general.dao.RecetteDao;
import com.general.dao.RecetteIngredientDao;
import com.general.dao.UniteDao;
import com.general.dao.UserDao;
import com.general.dto.CourseCategorieDto;
import com.general.dto.CourseListDto;
import com.general.dto.CourseParamDto;
import com.general.dto.UserDto;
import com.general.model.Ingredient;
import com.general.model.Planning;
import com.general.model.Recette;
import com.general.model.RecetteIngredient;
import com.general.model.User;
import com.general.security.TokenSecurity;
import com.general.service.CryptageService;

@Controller
@RestController
@RequestMapping(value = "/course")
public class CourseController {
	

	@Autowired
	RecetteDao recetteDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	CryptageService cryptageService;
	
	@Autowired
	TokenSecurity t;
    
    @Autowired
    IngredientDao ingredientDao;
    
    @Autowired
    RecetteIngredientDao recetteIngredientDao;
    
    @Autowired
    FavorisDao favorisDao;
    
    @Autowired
    UniteDao uniteDao;
    
    @Autowired
    PlanningDao planningDao;
    
    @Autowired
    EtapeDao etapeDao;
    
    @Autowired
    NoteDao noteDao;

    
    
    
	
	@RequestMapping(value = "/GenerationCourse", method = RequestMethod.POST,headers="Accept=application/json")
	@CrossOrigin(origins = "*")
	public Map<String, Object> GenerationCourse (@RequestBody CourseParamDto course)
	{
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		Map<Integer, CourseListDto> DicoCourseDto = new LinkedHashMap<>();
		
		Map<String,List<CourseListDto>>DicoCourseCategorieDto = new LinkedHashMap<>();
		int compteurRecette=0;
		
		User u = userDao.findUserByIdUser(course.getIdUser());
		List<Planning> lstPlanning=null;
		
		if(u!=null)
		{
			lstPlanning=planningDao.findPlanningByUserAndDate(u, course.getDateDebut(), course.getDateFin());
			compteurRecette=lstPlanning.size();
			for(Planning unPlanning : lstPlanning)
			{
				Recette uneRecette = recetteDao.findByIdRecette(unPlanning.getRecette().getIdRecette());
				List<RecetteIngredient> listRecetteIngredient = recetteIngredientDao.findAllByrecette(uneRecette);
				for(RecetteIngredient uneRecetteIngredient :listRecetteIngredient)
				{
					
					Ingredient unIngredient = ingredientDao.findByidIngredient(uneRecetteIngredient.getIngredient().getIdIngredient());
					CourseListDto crs = new CourseListDto();
					crs.setIdIngredient(unIngredient.getIdIngredient());
					crs.setLibelleIngredient(unIngredient.getLibelleIngredient());
					crs.setQuantite(uneRecetteIngredient.getQuantite());
					crs.setUnite(uneRecetteIngredient.getUnite().getLibelleUnite());
					crs.setCategorie(unIngredient.getCatIngredient());
					
					if(DicoCourseDto.containsKey(unIngredient.getIdIngredient()))
					{
						DicoCourseDto.get(unIngredient.getIdIngredient()).setQuantite(DicoCourseDto.get(unIngredient.getIdIngredient()).getQuantite()+crs.getQuantite());;
						
					}
					else
					{
						DicoCourseDto.put(unIngredient.getIdIngredient(), crs);
						
					}
					
				}
				
			}
			
		}
		
		List<CourseCategorieDto> lstTrier= new ArrayList<>();
		List<String> dist = ingredientDao.findAllDistinctCategorie();
		for(int i= 0; i< dist.size(); i++) {
			List<CourseListDto> listCourseByCat = new ArrayList<>();
			System.out.println(dist.get(i));
			DicoCourseCategorieDto.put(dist.get(i), listCourseByCat);
			lstTrier.add(new CourseCategorieDto(listCourseByCat, dist.get(i)));
			
		}
		
		if (DicoCourseDto.size()>0)
		{
			for(CourseListDto uneCourse:DicoCourseDto.values())
			{
	
				DicoCourseCategorieDto.get(uneCourse.getCategorie()).add(uneCourse);
				
			}
		}
		for(int i=0; i<lstTrier.size(); i++) {
			List<CourseListDto> listCourseByCat = DicoCourseCategorieDto.get(lstTrier.get(i).getCategorie());
			lstTrier.get(i).setListCourseDto(listCourseByCat);
		}
			
		mapReturn.put("listeCourse", DicoCourseCategorieDto);
		
		return mapReturn;
		
	}

}
