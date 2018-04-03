package com.cc.api.automation.libs;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;

import org.testng.Assert;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;

public class Post {
	ClientResponse response = null;
	 public  ClientResponse getPostByJersey(String posturl,String postparm,NewCookie newCookie){
		 
	    	  	   	
	    	
	            try {
	                Client client = Client.create();
	                WebResource webResource = client
	                   .resource(posturl);
	                 response = webResource.type("application/json")
	                		 .cookie(newCookie)
	                           .post(ClientResponse.class,postparm);
	              //  hMap = response.getHeaders();
	               
	                 System.out.println("Response Header ="+  response.toString());
	             	System.out.println("post staus"+response.getStatus());
	                if (response.getStatus() != 200) {
	                    throw new RuntimeException("Failed : HTTP error code : "
	                         + response.getStatus());
	                }
	                System.out.println("Output from Server .... \n");
	            
	               
	                String output = response.getEntity(String.class);
	                response.getProperties();
	                System.out.println(output);

	              String tempJson =output;

	//System.out.println(tempJson);

	
		

	//System.out.println("Response Header ="+  response.toString());
	System.out.println("post staus"+response.getStatus());

	/*
	    String[] parts = tempJson.split(",");
	    HashMap<String,String> jsonHash = new HashMap<String,String>();

	    for(int i=0;i<parts.length;i++){
	        parts[i]    =   parts[i].replace("\"", "");
	        parts[i]    =   parts[i].replace("{", "");
	        parts[i]    =   parts[i].replace("}", "");
	        String[] subparts = parts[i].split(":");
	        jsonHash.put(subparts[0],subparts[1]);
	    }       
	    System.out.println("Token="+jsonHash.toString());
	   // System.out.println("Response Header ="+  response.getHeaders());
	  // System.out.println("Token="+jsonHash.get("Token"));
	 //   System.out.println("Session id="+jsonHash.get("SessionID"));
	    
	  //  System.out.println(jsonHash.get("set-cookie"));
	    System.out.println(jsonHash.size());
	    System.out.println(jsonHash);
	    System.out.println(jsonHash.values());
	        
	    */           
	                
	                
	                
	              } catch (Exception e) {
	                System.out.println(e.getMessage());
	                
	              }
				return response;
	            
	              
	    
	            }
	 
	 
	 
	 //multivalued post with attachments
	 
	 public ClientResponse postMultiValuedWithAttachments(String postUrl,HashMap<String,InputStream> fileloc,FormDataMultiPart formData,NewCookie newCookie)
	 {
		 try {
             Client client = Client.create();
             WebResource webResource = client
                .resource(postUrl);
             ClientResponse response = null;
             System.out.println("Call is starting");
          		   try {
          			   response = webResource.accept("application/json")
                     //.type("MediaType.MULTIPART_FORM_DATA_TYPE")
                     .header("Content-Type", "multipart/form-data")
                     .cookie(newCookie)
                     .type(MediaType.MULTIPART_FORM_DATA)
                     
                     .post(ClientResponse.class,formData);
          			   
          			 System.out.println("Output from Server .... \n");
              		 System.out.println("post staus with form data"+response.toString());
                 System.out.println("Response Header ="+  response.getHeaders());
                 System.out.println("post staus"+response.getStatus());
          		   } catch(Exception e) {
          			   System.out.println("Error is having");
          			   System.out.println(e);
          		   }
         
          		
             

            
             
             
             String output = response.getEntity(String.class);
             response.getProperties();
             System.out.println(output);
         
             
             if (response.getStatus() != 200) {
                 throw new RuntimeException("Failed : HTTP error code : "
                      + response.getStatus());
             }





	


             
           } catch (Exception e) {
             System.out.println(e.getMessage());
             
           }  
  	   
  	   finally {
  			if (fileloc.get("f1") != null) {
  				try {
  					fileloc.get("f1").close();
  					fileloc.get("f2").close();
  					fileloc.get("f3").close();
  					fileloc.get("f4").close();
  					fileloc.get("f5").close();
  				} catch (IOException e) {
  					e.printStackTrace();
  				}
  			}
  			else
  			{
  				System.out.println("done");
  			} }
		
		return response;
  			
	 }
	 
	 
	 
	 
	 
	 public  ClientResponse getPostByJersey(String posturl,String postparm){
		 
	  	   	
	    	
         try {
             Client client = Client.create();
             WebResource webResource = client
                .resource(posturl);
              response = webResource.type("application/json").post(ClientResponse.class,postparm);
           //  hMap = response.getHeaders();
            System.out.println(response);
              System.out.println("Response Header ="+  response.toString());
          	System.out.println("post staus"+response.getStatus());
             if (response.getStatus() != 200) {
                 throw new RuntimeException("Failed : HTTP error code : "
                      + response.getStatus());
             }
             System.out.println("Output from Server .... \n");
         
            
             String output = response.getEntity(String.class);
             response.getProperties();
             System.out.println(output);

           String tempJson =output;

//System.out.println(tempJson);


	

//System.out.println("Response Header ="+  response.toString());
System.out.println("post staus"+response.getStatus());

/*
 String[] parts = tempJson.split(",");
 HashMap<String,String> jsonHash = new HashMap<String,String>();

 for(int i=0;i<parts.length;i++){
     parts[i]    =   parts[i].replace("\"", "");
     parts[i]    =   parts[i].replace("{", "");
     parts[i]    =   parts[i].replace("}", "");
     String[] subparts = parts[i].split(":");
     jsonHash.put(subparts[0],subparts[1]);
 }       
 System.out.println("Token="+jsonHash.toString());
// System.out.println("Response Header ="+  response.getHeaders());
// System.out.println("Token="+jsonHash.get("Token"));
//   System.out.println("Session id="+jsonHash.get("SessionID"));
 
//  System.out.println(jsonHash.get("set-cookie"));
 System.out.println(jsonHash.size());
 System.out.println(jsonHash);
 System.out.println(jsonHash.values());
     
 */           
             
             
             
           } catch (Exception e) {
             System.out.println(e.getMessage());
             
           }
			return response;
         
           
 
         }

	    
}
