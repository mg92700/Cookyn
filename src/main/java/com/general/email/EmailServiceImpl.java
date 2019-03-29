package com.general.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.general.dao.UserDao;
import com.general.model.User;
import com.google.api.gax.rpc.Transport;

import javax.mail.internet.*;


public class EmailServiceImpl   {
  
   

    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
	UserDao userDao;
    
    
    
	 @Value("${loginEmail}")
	    private String loginEmail;
	
	 @Value("${passWordEmail}")
	    private String passWordEmail;
	
    public void sendSimpleMessage(

    	      String to, String subject,User u) throws AddressException, MessagingException {{
    	    	  
    	    	  
    	    	  
    	    	 
    	    		String urlEnligne= " http://51.75.22.154:8080/Cookyn3/user/VerifUserMail/"+u.getIdUser();
    	    		String urlLocal="http://localhost:8080/General/user/VerifUserMail/"+u.getIdUser();
    	    		
    	    		String button="<a href="+urlLocal+" <button type=\"button\" class=\"btn btn-primary\">Confirmer</button></a>";
    	    		
    	    		 /*String text="Bonjour "+u.getNomUser() +  "  "+u.getPrenomUser() +" veuillez valider votre email: "
    	    	    	  		+ button;
    	    	    	  	*/
    	    	

    	            String text="<html>\n" + 
    	            		"<head>\n" + 
    	            		"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" + 
    	            		"</head>\n" + 
    	            		"<body>"+
    	            		"Bonjour "+u.getNomUser() +  "  "+u.getPrenomUser() +" veuillez valider votre email: "+
    	            		button+
    	            			"</body>"+
    	            		"</html>"
    	            		;

    	           Properties props = new Properties();

    	           props.put("mail.transport.protocol", "smtp");

    	           props.put("mail.smtp.auth", "true");

    	           props.put("mail.smtp.starttls.enable", "true");

    	           props.put("mail.debug", "true");
 	
    	   		
    	   			props.put("mail.smtp.host", "smtp.gmail.com");
    	   			props.put("mail.smtp.port", "587");
    	   			

    	   			Session session = Session.getInstance(props,
    	   			  new javax.mail.Authenticator() {
    	   				protected PasswordAuthentication getPasswordAuthentication() {
    	   					return new PasswordAuthentication(loginEmail, passWordEmail);
    	   				}
    	   			  });

    	           

    	   			try {

    	   				MimeMessage message = new MimeMessage(session);
    	   				message.setFrom(new InternetAddress(loginEmail));
    	   				message.setRecipients(Message.RecipientType.TO,
    	   					InternetAddress.parse(to));
    	   				message.setSubject(subject);
    	   				
    	   				message.setContent(text, "text/html");

    	   				javax.mail.Transport.send(message);

    	   				System.out.println("Done");

    	   			} catch (MessagingException e) {
    	   				throw new RuntimeException(e);
    	   			}
    	 

    	    }
}
    
}
