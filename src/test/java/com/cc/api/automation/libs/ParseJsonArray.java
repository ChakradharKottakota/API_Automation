package com.cc.api.automation.libs;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;

public class ParseJsonArray {


	public static Object parseJsonArray(String res,String data,String docs,String id)
	{
		 Object reqid=null;
		JSONObject obj = new JSONObject(res);
		JSONArray docsArray = new JSONObject(res).getJSONObject(data).getJSONArray(docs);
		JSONObject obj2 = null;

		List li = new ArrayList();
		for (Object object : docsArray) {
			obj2 = new JSONObject(object.toString());
			// System.out.println("The id is ========="+ obj2.get(id));
		}

		//System.out.println("The id is =========" + obj2.get(id));
		reqid= obj2.get(id);
      return reqid;
	}

	public static Object parseJsonArray2(String res,String data1)
	{
		JSONObject obj = new JSONObject(res);
		JSONArray docsArray = obj.getJSONArray("data");
        JSONObject obj2 = null;

        List list = new ArrayList();
        for (Object object : docsArray) {
               obj2 = new JSONObject(object.toString());
               obj2.get("_id");
              System.out.println( obj2.get("_id"));
        }
		return null;
	
	
	
} }
