package com.cc.api.automation.libs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Get {
	String id0nly = null;

	// Get Api Request with json response

	public ClientResponse getRestServiceMethod(String geturl, NewCookie newCookie) {

		ClientResponse response = null;
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(geturl);
			response = webResource.accept("application/json").type("application/json")
					.header("Content-Type", "application/json;charset=UTF-8").cookie(newCookie)
					.get(ClientResponse.class);
			String output = response.getEntity(String.class);
			System.out.println("*****************From Get Client  Class **************");
			System.out.println("Output from Server .... \n");
			System.out.println("Response=" + response.toString());
			System.out.println(response.getStatus());
			// System.out.println(response.getHeaders().toString());
			System.out.println("output" + output);
			String tempJson = output;

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println("Exception at get jersey " + e.getMessage());
			
		}
		return response;

	}

	public String getRestServiceMethod2(String geturl, NewCookie newCookie) {
		HashMap<String, String> jsonHash = new HashMap<String, String>();
		ClientResponse response = null;
		Map<String, Object> mp = null;
		String output = null;
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(geturl);
			response = webResource.accept("application/json").type("application/json")
					.header("Content-Type", "application/json;charset=UTF-8").cookie(newCookie)
					.get(ClientResponse.class);
			output = response.getEntity(String.class);
			System.out.println("*****************From Get Client  Class **************");
			System.out.println("Output from Server .... \n");
			// System.out.println("Response="+response.toString());
			System.out.println(response.getStatus());
			// System.out.println(response.getHeaders().toString());
			// System.out.println("output"+output);
			// String tempJson =output;
			/*
			 * JSONObject obj=new JSONObject(output); JSONArray docsArray = new
			 * JSONObject(output).getJSONObject("data").getJSONArray("docs");
			 * 
			 * 
			 * List li=new ArrayList(); for (Object object : docsArray) { JSONObject obj2
			 * =new JSONObject(object.toString()); System.out.println("The id is "+
			 * obj2.get("_id")); }
			 */

			mp = new HashMap<String, Object>();
			mp.put("Response", response);
			// mp.put("resparray", obj);
			// HttpEntity entity = response.getEntity(null);
			// System.out.println(EntityUtils.toString(entity));

			/*
			 * String tempJson = null; String[] parts = tempJson.split(",");
			 * 
			 * for(int i=1;i<3;i++){ parts[i] = parts[i].replace("\"", "");
			 * System.out.println("parts \\" + parts[i] ); parts[i] = parts[i].replace("{",
			 * ""); System.out.println("parts ,"+parts[i] ); parts[i] =
			 * parts[i].replace("[", ""); System.out.println(" parts }"+ parts[i]); String[]
			 * subparts = parts[i].split(":"); System.out.println("subparts="+subparts);
			 * System.out.println("subparts[0]"+subparts[0]);
			 * System.out.println("subparts[1]"+subparts[1]);
			 * System.out.println("subparts[2]"+subparts[2]);
			 * System.out.println("subparts[3]"+subparts[3]); id0nly= subparts[3];
			 * //jsonHash.put(subparts[0],subparts[1]);
			 * //jsonHash.put(subparts[2],subparts[3]); //
			 * jsonHash.put(response,subparts[3]); // parts[i] = parts[i].replace("\"", "");
			 */

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			Assert.assertEquals(200, response.getStatus());
		} catch (Exception e) {
			System.out.println("Exception at get jersey " + e.getMessage());
			
		}
		return output;

	}

}
