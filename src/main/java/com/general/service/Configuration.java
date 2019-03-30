package com.general.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Configuration {
	
	
	

	InputStream inputStream;
	
	public Map<String, String> GetConfiguration() throws IOException
	{
		Map<String, String> map = new HashMap<>(); 
		
		try {
			Properties prop = new Properties();
			String propFileName = "application.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 

 
			// get the property value and print it out
			
			map.put("loginFtp", prop.getProperty("loginFtp"));
			map.put("PasswordFtp", prop.getProperty("PasswordFtp"));
			map.put("loginEmailL", prop.getProperty("loginEmailL"));
			map.put("passWordEmail", prop.getProperty("passWordEmail"));
	
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return map;
	}

}
