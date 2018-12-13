package com.general;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.general.security.Token;
import com.general.service.ServiceImageFtp;

public class main {

	  public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			/*
			try {
				f.resultat("hamid", "poulet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		/*	Token t = new Token();
			String token = t.getToken();
			if(t.VerifTokenActif(token)==true)
			{
				System.out.print("valide");
				
			}
			else 
			{
				System.out.print("pas valide");
			}
*/
		  
		  
		  ServiceImageFtp s = new ServiceImageFtp();

          // APPROACH #1: using retrieveFile(String, OutputStream)
          String remoteFile1 = "/Users/mouhsin/Documents/antoine.jpg";

          
          
          File fi = new File(remoteFile1);
          byte[] fileContent = Files.readAllBytes(fi.toPath());
          System.out.println(fileContent.toString());
          
          String ok=s.resultat("cestcakenfzaufbezdiuazcNFGZEYmouhsin","antoine",fileContent);
          System.out.println(ok);
		  
		}

}
