package com.general.security;
import org.apache.commons.codec.binary.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


@WebFilter("/admin/*")
public class BasicAuthenticationFilter implements Filter{

	  
	  private Token t = new Token();

	  
	  private String notFoundImage;
	 
	 @Override
	  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	      throws IOException, ServletException {

	    HttpServletRequest request = (HttpServletRequest) servletRequest;
	    HttpServletResponse response = (HttpServletResponse) servletResponse;

	    
	    Map<String, String> map = new HashMap<String, String>();
	    Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        //recupere value depuis la clés
        String Token=map.get("authorization");
	    if (Token != null) 
	    {
	    	
	    //  StringTokenizer st = new StringTokenizer(Token);
	     
	              if (t.VerifTokenActif(Token)) 
	              {
	            	  filterChain.doFilter(servletRequest, servletResponse);
	            		
	              
	                
	              }

	             else {
	            	 response.setStatus(HttpServletResponse.SC_FORBIDDEN,"Incorrecte");
	            	
	            
	            }
	       
	    } 
	 else {
		 response.setStatus(HttpServletResponse.SC_FORBIDDEN,"Incorrecte");
	      
	    }
	  }
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	  private void unauthorized(HttpServletResponse response) throws IOException {
		    unauthorized(response);
		  }
	

}
