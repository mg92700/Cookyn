package com.general.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.general.dao.UniteDao;
import com.general.dao.UserDao;
import com.general.dto.CourseDto;
import com.general.dto.UserDto;
import com.general.model.Planning;
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
	public Map<String, Object> GenerationCourse (CourseDto course)
	{
		//List<String> lstString= new ArrayList<String>();
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		
		User u = userDao.findUserByIdUser(course.getIdUser());
		
		if(u!=null)
		{
			List<Planning> lstPlanningByUser = planningDao.findAllByuser(u);
			List<Planning> lstNewWithFilterDate = new ArrayList<Planning>();
			
			for (Planning p : lstPlanningByUser)
			{
				if (p.getDatePlanning().compareTo(course.getDateDebut()) == -1 && p.getDatePlanning().compareTo(course.getDateFin())==1 ) { 
					lstNewWithFilterDate.add(p);
				} 
				
			}
		
		
			if(lstNewWithFilterDate.size()>0)
			{
				
				
				
			}
		
		}
		return mapReturn;
		
	}

}
