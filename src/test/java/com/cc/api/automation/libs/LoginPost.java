package com.cc.api.automation.libs;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginPost {
//	static MultivaluedMap<String, String> hMap;
	 static NewCookie newCookie;
	
	 
   public  NewCookie loginGetPostByJersey(String posturl,String postparm){
   	//String cookieString = null;
   	  	   	
   	
           try {
               Client client = Client.create();
               WebResource webResource = client
                  .resource(posturl);
               ClientResponse response = webResource.type("application/json")
                          .post(ClientResponse.class,postparm);
        //       hMap = response.getHeaders();
              
               System.out.println("Output from Server :Core Library:.... \n");
               System.out.println("Response="+response.toString());
               System.out.println("Response status="+response.getStatus());
               System.out.println("Response Header ="+  response.getHeaders());
               if (response.getStatus() != 200) {
                   throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
               }
               
           
              
               String output = response.getEntity(String.class);
               response.getProperties();
               System.out.println(output);
                System.out.println(response.getProperties().toString());
                
             String tempJson =output;

System.out.println( response.getCookies().get(0));

newCookie = response.getCookies().get(0);

//if(cookies.size() >=1) {
//	for(NewCookie nCookie : cookies) {
//		newCookie = nCookie;
//	}
//}
	


//System.out.println("post staus"+response.getStatus());

 
               
               
             } catch (Exception e) {
               System.out.println(e.getMessage());
               
             }
           System.out.println("##################################");
           //System.out.println(newCookie);
           return newCookie;
           
           
           
           
   
           }
   
   

}
