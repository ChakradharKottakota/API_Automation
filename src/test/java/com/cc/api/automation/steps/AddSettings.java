package com.cc.api.automation.steps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cc.api.automation.extent.ExtentManager;
import com.cc.api.automation.libs.CertificateHandle;
import com.cc.api.automation.libs.FilloExcelUtility;
import com.cc.api.automation.libs.Post;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jersey.api.client.ClientResponse;

public class AddSettings {
	
	Post post =new Post();
	ExtentReports extent;
	static ExtentTest test;
	ClientResponse response = null;
	HashMap<String, LinkedHashMap<Integer, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();
	HashMap<String, String> testdatamap = null;
	
	
	@BeforeClass
	
	public void initateExtentManager() {
		extent = ExtentManager.Instance();
	}

	@Test(priority = 1)
	public void login_to_application() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: Add Settings");

//		try {
//			CertificateHandle.IgnoreSSLClient();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String p = null;

		// String posturl="https://test.redington.market/api/v1/auth/signIn";
		// String
		// postparmdup="{\"username\":\"pm6.qa@mailinator.com\",\"'"+p+"'\":\"Pass@123\"}";

		String posturl = "https://cc.rlcatalyst.com/settings";
				//testdatamap.get("addNewBusinessService");
		System.out.println("Login Url=" + posturl);
		String postparm = "{\n  \"url\": \"https://www.Google.com\",\n  \"name\": \"AutoTest123 Service\",\n  \"group\": \"AutoTest123\",\n  \"sub_group\": \"AutoTest123\",\n  \"interval\": \"Minutes\",\n  \"repeat_every\": 2\n}";
		System.out.println(posturl );
		System.out.println(postparm);
		ClientResponse res =post.getPostByJersey(posturl,postparm);
		//		String username = testdatamap.get("username");
//		System.out.println("User name==" + username);
//		String password = testdatamap.get("password");
//		System.out.println("Password==" + password);

		//String postparm = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		// String
		// postparm="{\"username\":\"pm6.qa@mailinator.com\",\"password\":\"Pass@123\"}";

		//cook = lgoin.loginGetPostByJersey(posturl, postparm);
		//System.out.println("Cookie as Token paramter=" + cook);

		//logPassStatus("login to Application with Userid=pm6.qa@mailinator.com Password=Pass@123");
		logPassStatus("New settings has been added sucessfully");
	}
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			WebDriver driver = null;
			test.log(LogStatus.FAIL,result.getThrowable()+
			test.addScreenCapture(ExtentManager.CaprtureScreenshot(driver,result.getInstanceName())));
			test.log(LogStatus.FAIL, result.getThrowable());
		}

	}
	
	@AfterClass
	public void tear() {
		extent.endTest(test);
		extent.flush();
	}

	public void logPassStatus(String statusMsg) {
		test.log(LogStatus.PASS, statusMsg);

	}
}
