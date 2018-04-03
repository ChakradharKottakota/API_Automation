package com.cc.api.automation.util;

import java.util.HashMap;

import com.cc.api.automation.libs.ExcelReader;
import com.cc.api.automation.libs.FilloExcelUtility;

public class TestDb {
	public  void testdatampa()
	{
		FilloExcelUtility excl=new FilloExcelUtility();
		HashMap<String, String> testdata = excl.readExcel();
	//	HashMap<String, String> testdata=FilloExcelUtility.readExcel();
	//	System.out.println("test data="+testdata.get("login1"));
		
		excl.replaceRow("dumb", "dumb100", "dumb");
		
	}
	
	public static void main(String args[])
	{
		TestDb db=new TestDb();
		
	db.testdatampa();
	}
	
	
}
