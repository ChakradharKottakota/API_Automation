package com.cc.api.automation.libs;

import java.util.HashMap;

import com.cc.api.automation.utils.Configurations;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;


public class FilloExcelUtility {
	
	
	/***For Windows***/
	//static String  exceldbloc="E:\\\\BackUp\\\\New API Auto\\\\CommandCenter\\\\src\\\\test\\\\resources\\\\files\\\\ConfigSheet.xls";
	
	/*Path armtemp = Paths.get("./src/Filesupload/azureTestTemplate.json");
    Path armTemp = armtemp.toRealPath(LinkOption.NOFOLLOW_LINKS);
     System.out.println(armTemp);*/
	//static String  exceldbloc="F:\\\\workspace\\\\backup_only_my_changes\\\\GenesisUIAutomation\\\\src\\\\test\\\\resources\\\\files\\\\ConfigSheet.xlsx";
	
	
	/***For  Linux***/
	static String  exceldbloc="/var/lib/jenkins/jobs/API_Automation/src/test/resources/files/ConfigSheet.xlsx";

	/*For client linux env*/
	//	static String  exceldbloc="/home/genadmin/workspace/Genesis_API_Automation_new/src/test/resources/files/ConfigSheet.xlsx";
	
	
    
    
    
	public static HashMap<String,String> readExcel(){
	{
	
	System.out.println("exceldbloc Location="+exceldbloc);	
		
		HashMap<String, String> dataMap= new HashMap<String, String>();
		Fillo fillo=new Fillo();
		Connection connection = null;
		try {
			Configurations config=new Configurations();
			//String fileLoc=config.getProperty("excelLoc");
			System.out.println("exceldbloc="+exceldbloc);
			connection = fillo.getConnection(exceldbloc);
			
			
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strQuery="Select * from Sheet ";
		
		Recordset recordset = null;
		try {
			recordset = connection.executeQuery(strQuery);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			while(recordset.next()){
				 
				
				String key = recordset.getField("key");
				String value = recordset.getField("value");
			dataMap.put(key, value);
			//	System.out.println(recordset.getField("key"));
			//	System.out.println(recordset.getField("value"));
				 //value = recordset.getString("value");
				dataMap.put(key, value);
		//	System.out.println(recordset.getField("key"));
			//System.out.println(dataMap.get("login3"));
			
			
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		recordset.close();
		connection.close();
		//System.out.println("test data="+dataMap.get("login1"));
		return dataMap;
	}
	}
	//Select Data From Particular Page
	public static HashMap<String,String> readExcelWithTestName(String page)
	{
	
	System.out.println("exceldbloc Location="+exceldbloc);	
		
		HashMap<String, String> dataMap= new HashMap<String, String>();
		Fillo fillo=new Fillo();
		Connection connection = null;
		try {
			Configurations config=new Configurations();
			String fileLoc=config.getProperty("excelLoc");
			System.out.println("exceldbloc="+exceldbloc);
			connection = fillo.getConnection(exceldbloc);
			
			
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String strQuery="Select * from Sheet where page="+page+"";
		System.out.println("Page Name="+page);
		Recordset recordset = null;
		try {
			recordset = connection.executeQuery("Select * from Sheet").where("page='"+page+"'");
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			while(recordset.next()){
				 
				
				String key = recordset.getField("key");
				String value = recordset.getField("value");
			dataMap.put(key, value);
			
				dataMap.put(key, value);
		
			
			
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		recordset.close();
		connection.close();
		
		return dataMap;
	}
	
	
	public static void updateRow(String key,String page)
	{
		Fillo fillo=new Fillo();
		Connection connection = null;
		String pulledkey=null;
		
		
		try {
			
				connection = fillo.getConnection(exceldbloc);
				
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//Recordset recordset=connection.executeQuery("Select * from Sheet").where("key='"+key+"').where("page='"+page+"');
			Recordset recordset=connection.executeQuery("Select * from Sheet").where("key='"+key+"'").where("page='"+page+"'");
while(recordset.next()){
	
				
				 key = recordset.getField("key");
				  String value = recordset.getField("value");
			
				System.out.println(recordset.getField("key"));
				System.out.println(recordset.getField("value"));
				pulledkey=recordset.getField("value");
				
				String string = pulledkey;
				System.out.println("code:" + string);
				String number = "";
				String letter = "";
				for (int i = 0; i < string.length(); i++) {
					char a = string.charAt(i);
					if (Character.isDigit(a)) {
						number = number + a;
					} else {
						letter = letter + a;
					}
				}
				System.out.println("Alphates in string:" + letter);
				System.out.println("Numbers in String:" + number);
				int y = Integer.parseInt(number);
				System.out.println("New Integer Value" + y);
				y = y + 1;
				pulledkey = letter + y;
				System.out.println("updated value=" + pulledkey);
		} }
catch (FilloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = fillo.getConnection(exceldbloc);
		} catch (FilloException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String strQuery1="Update Sheet set  value='"+pulledkey+"' where key='"+key+"' and page='"+page+"'" ;
		//String strQuery1="Update Sheet set  value='"+pulledkey+"' where page='"+page+"'" ;

		try {
			connection.executeUpdate(strQuery1);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		connection.close();
			
	}
	
	
	public static void  replaceRow(String key,String value,String page)
	{
		Fillo fillo=new Fillo();
		Connection connection = null;
		String pulledkey=null;
		
		
		try {
			
				connection = fillo.getConnection(exceldbloc);
				
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strQuery="Update Sheet Set value='"+value+"' where key='"+key+"' and page='"+page+"'";
		 
		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		connection.close();
	}
	
	
	
	
	public static void  replaceRowPartnereEntity(String key,String value,String page)
	{
		Fillo fillo=new Fillo();
		Connection connection = null;
		String pulledkey=null;
		
		
		try {
			
				connection = fillo.getConnection(exceldbloc);
				
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strQuery="Update Sheet Set value='"+value+"' where key='"+key+"' and page='"+page+"'";
		 
		try {
			connection.executeUpdate(strQuery);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		connection.close();
	}
	
	
	
	
	public static void main(String args[])
	{
		

		FilloExcelUtility rl=new FilloExcelUtility();
		//rl.updateRow("login1","login");
		//FilloExcelUtility.updateRow("login1", "dev");
		FilloExcelUtility.updateRow("domainName", "AddNewPartnerOrganisationTest");
		//rl.readExcel();
	}
}
