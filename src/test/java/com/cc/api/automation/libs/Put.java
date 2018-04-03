package com.cc.api.automation.libs;

import javax.ws.rs.core.NewCookie;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import junit.framework.Assert;

public class Put {
	ClientResponse response = null;
	 public  ClientResponse put(String posturl,String postparm,NewCookie newCookie){
		 
	    	  	   	
	    	
	            try {
	                Client client = Client.create();
	                WebResource webResource = client
	                   .resource(posturl);
	                 response = webResource.type("application/json")
	                		// .header("user-role", "BAM")
	                		 .cookie(newCookie)
	                           .put(ClientResponse.class,postparm);
	              //  hMap = response.getHeaders();
	               
	               
	                 System.out.println("Output from Server for put Call .... \n");
	            	System.out.println("Response Header ="+  response.toString());
	            	System.out.println("post staus"+response.getStatus());
	            	//System.out.println("post staus"+response.toString());
	            
	            	
	             	if (response.getStatus() != 200) {
	                    throw new RuntimeException("Failed : HTTP error code : "
	                         + response.getStatus());
	                }
	              
	             
	               
	               
	          

	
	                
	              } catch (Exception e) {
	                System.out.println(e.getMessage());
	                
	              }
				return response;
	            
	              
	    
	            }

}
