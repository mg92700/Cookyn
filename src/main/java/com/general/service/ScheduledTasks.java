package com.general.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.general.dao.RecetteDao;
import com.general.dao.StatistiqueDao;
import com.general.dao.UserDao;
import com.general.model.Recette;
import com.general.model.Statistique;
import com.general.model.User;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    

	@Autowired
	UserDao userDao;

    @Autowired
    RecetteDao recetteDao;
    

    @Autowired
    StatistiqueDao statistiqueDao;
    
    /*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
    String d = dateFormat.format(date).toString()*/
	Date date = new Date();
    
    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        log.info("Début traitement le {}", dateFormat.format(new Date()));
        

        NombreUser();
        NombreUserConnecter();
        moyenneRecettesByUser();
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        log.info("Fin traitement le {}", dateFormat.format(new Date()));
        
    }
    
		private void moyenneRecettesByUser() {
		    	
		    	
			Statistique s =statistiqueDao.findByLibelle("Moyenne de recette par utilisateur");
			
			if(s==null)
			{
		    	Statistique uneStatisque = new Statistique();
		    	String value=null;
				//On récupère d'abord le nombre total de recettes
		      
			    	List<Recette> recettes = recetteDao.findAll();
			        int nbRecettes = recettes.size();
			
					//Puis on récupère le nombre total d'utilisateurs
			        List<User> users = userDao.findAll();
			        int nbUsers = users.size();
			
					//On divise ensuite le nombre de recettes par le nombre d'utilisateurs
			        float  moyen= (float) nbRecettes / nbUsers;
			        value=String.valueOf(moyen);
		      
		        
		        uneStatisque.setLibelleStatistique("Moyenne de recette par utilisateur");
		        uneStatisque.setDateStatistique(date);
		        uneStatisque.setValeurStatistique(value);
		        statistiqueDao.save(uneStatisque);
			}
			else {
				
				
				String value=null;
				//On récupère d'abord le nombre total de recettes
		      
			    	List<Recette> recettes = recetteDao.findAll();
			        int nbRecettes = recettes.size();
			
					//Puis on récupère le nombre total d'utilisateurs
			        List<User> users = userDao.findAll();
			        int nbUsers = users.size();
			
					//On divise ensuite le nombre de recettes par le nombre d'utilisateurs
			        float  moyen= (float) nbRecettes / nbUsers;
			        value=String.valueOf(moyen);
			        s.setDateStatistique(date = new Date());
			    
			        s.setValeurStatistique(value);
			        statistiqueDao.save(s);
			}
			
		}

		private void NombreUser() {
		    	
		    	
			Statistique s =statistiqueDao.findByLibelle("Nombre d'utilisateur");
			
			if(s==null)
			{
		    	Statistique uneStatisque = new Statistique();
		    	String value=null;
			
			
					//Puis on récupère le nombre total d'utilisateurs
			        List<User> users = userDao.findAll();
			        int nbUsers = users.size();
		
			        value=String.valueOf(nbUsers);
		      
		        
		        uneStatisque.setLibelleStatistique("Nombre d'utilisateur");
		        uneStatisque.setDateStatistique(date);
		        uneStatisque.setValeurStatistique(value);
		        statistiqueDao.save(uneStatisque);
			}
			else {
				
				
			 	String value=null;
				
				
				//Puis on récupère le nombre total d'utilisateurs
		        List<User> users = userDao.findAll();
		        int nbUsers = users.size();
	
		        value=String.valueOf(nbUsers);
			        s.setDateStatistique(date = new Date());
			        s.setValeurStatistique(value);
			        statistiqueDao.save(s);
			}
			
		}
		
		private void NombreUserConnecter()
		{
			Statistique s =statistiqueDao.findByLibelle("Nombre d'utilisateur connecter");
			
			if(s==null)
			{
		    	Statistique uneStatisque = new Statistique();
		    	String value=null;
			
			
					//Puis on récupère le nombre total d'utilisateurs
			        List<User> users = userDao.findAllUserConnecte();
			        int nbUsers = users.size();
		
			        value=String.valueOf(nbUsers);
		      
		        
		        uneStatisque.setLibelleStatistique("Nombre d'utilisateur connecter");
		        uneStatisque.setDateStatistique(date = new Date());
		        uneStatisque.setValeurStatistique(value);
		        statistiqueDao.save(uneStatisque);
			}
			else {
				
				
			 	String value=null;
				
				
				//Puis on récupère le nombre total d'utilisateurs
		        List<User> users = userDao.findAllUserConnecte();
		        int nbUsers = users.size();
	
		        value=String.valueOf(nbUsers);
			        s.setDateStatistique(date);
			        s.setValeurStatistique(value);
			        statistiqueDao.save(s);
			}
			
		}

		
		
}