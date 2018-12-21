package com.general.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.dao.UniteDao;
import com.general.model.Unite;
@Transactional
@Service
public class ConvertUnite {
	@Autowired
	UniteDao uniteDao;

	
	public float Convert(int idUnite,float value)
	{
		Unite u= uniteDao.findByidUnite(idUnite);
		float valueUnite=(float) 0;
		if(u!=null)
		{
			if(u.getLibelleUnite().equals("grammes"))
			{
				return value/1000;
			}
			else if(u.getLibelleUnite().equals("kilogrammes"))
			{
				return value*1000;
				
			}
			else if(u.getLibelleUnite().equals("cuillerée à café"))
			{
				return (float) (value*0.5);
				
			}
			else if(u.getLibelleUnite().equals("cuillerée à soupe"))
			{
				
				return (float) (value*1.5);
				
			}
			else if(u.getLibelleUnite().equals("litres"))
			{
				return value*1000;
				
			}
			else if(u.getLibelleUnite().equals("centilitre"))
			{
				return value/1000;
				
			}
			
		}
		return valueUnite;
		
	}
	
	public float ConvertToCentilitre(int idUnite,float value)
	{
		Unite u= uniteDao.findByidUnite(idUnite);
		float valueUnite=(float) 0;
		if(u!=null)
		{
		
			if(u.getLibelleUnite().equals("cuillerée à café"))
			{
				return (float) (value*0.5);
				
			}
			else if(u.getLibelleUnite().equals("cuillerée à soupe"))
			{
				
				return (float) (value*1.5);
				
			}
		/*
			else if(u.getLibelleUnite().equals("centilitre"))
			{
				return value/1000;
				
			}
			*/
		}
		return valueUnite;
		
	}

	public float ConvertToLitre(int idUnite,float value)
	{
		Unite u= uniteDao.findByidUnite(idUnite);
		float valueUnite=(float) 0;
		if(u!=null)
		{
			if(u.getLibelleUnite().equals("litres"))
			{
				return value*1000;
				
			}
			
		}
		return valueUnite;
		
	}

	public float ConvertToGramme(int idUnite,float value)
	{
		Unite u= uniteDao.findByidUnite(idUnite);
		float valueUnite=(float) 0;
		if(u!=null)
		{
			if(u.getLibelleUnite().equals("grammes"))
			{
				return value/1000;
			}
			
		}
		return valueUnite;
		
	}

	public float ConvertToKilogramme(int idUnite,float value)
	{
		Unite u= uniteDao.findByidUnite(idUnite);
		float valueUnite=(float) 0;
		if(u!=null)
		{
			 if(u.getLibelleUnite().equals("kilogrammes"))
			{
				return value*1000;
				
			}
	
		}
		return valueUnite;
		
	}


}
