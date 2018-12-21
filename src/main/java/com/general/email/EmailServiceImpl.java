package com.general.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.general.dao.UserDao;
import com.general.model.User;


public class EmailServiceImpl   {
  
    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
	UserDao userDao;
    
    
    public void sendSimpleMessage(

    	      String to, String subject,int idUser) throws AddressException, MessagingException {{

    	    	  
    	    	  User u =userDao.findUserByIdUser(idUser);
    	    	  
    	    	  
    	    	  String text="Bonjour "+u.getNomUser() + u.getPrenomUser() +" veuillez valider votre email:"
    	    	  		+ "";
    	    	  		

    	      

    	    	  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    	           mailSender.setHost("smtp.gmail.com");

    	           mailSender.setPort(587);

    	           

    	           mailSender.setUsername("ycookyn@gmail.com");

    	           mailSender.setPassword("P@sscookyn92");

    	            

    	           Properties props = mailSender.getJavaMailProperties();

    	           props.put("mail.transport.protocol", "smtp");

    	           props.put("mail.smtp.auth", "true");

    	           props.put("mail.smtp.starttls.enable", "true");

    	           props.put("mail.debug", "true");

    	           props.put("mail.smtp.ssl","true");

    	           

    	        SimpleMailMessage message = new SimpleMailMessage();

    	        message.setTo(to);

    	        message.setSubject(subject);

    	        message.setText(text);

    	        message.setFrom("ycookyn@gmail.com");

    	        mailSender.send(message);

    	 

    	    }
}
    
}