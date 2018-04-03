package com.cc.api.automation.libs;

import javax.ws.rs.core.NewCookie;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Delete {

	ClientResponse response = null;
	 public  ClientResponse delete(String posturl,NewCookie newCookie){
		 
	    	  	   	
	    	
	            try {
	                Client client = Client.create();
	                WebResource webResource = client
	                   .resource(posturl);
	                 response = webResource.type("application/json")
	                		 .cookie(newCookie)
	                           .delete(ClientResponse.class);
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
	System.out.println("Response Header ="+  response.toString());
	System.out.println("post staus"+response.getStatus());
                  
	              } catch (Exception e) {
	                System.out.println(e.getMessage());
	                
	              }
				return response;
	            
	              
	    
	            }
	 
	 
	
}
