package com.cc.api.automation.steps;

import java.util.HashMap;

import javax.ws.rs.core.NewCookie;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cc.api.automation.extent.ExtentManager;
import com.cc.api.automation.libs.FilloExcelUtility;
import com.cc.api.automation.libs.Get;
import com.cc.api.automation.libs.LoginPost;
import com.cc.api.automation.libs.Post;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.FormDataMultiPart;

public class AddNewBusinessService {
	static NewCookie cook;
	LoginPost lgoin = new LoginPost();
	Get get = new Get();
	ExtentReports extent;
	String s;
	static ExtentTest test;
	ClientResponse response = null;
	
	HashMap<String, String> testdatamap = null;
	Post postcall = new Post();

	@BeforeClass
	public void initateExtentManager() {
		extent = ExtentManager.Instance();
	}

	@Test(priority = 1)
	public void login_to_application() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: AddNewBusinessService");

		String posturl = testdatamap.get("loginurl");
		System.out.println("Login Url=" + posturl);
		String username = testdatamap.get("username");
		System.out.println("User name==" + username);
		String password = testdatamap.get("password");
		System.out.println("Password==" + password);

		String postparm = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		cook = lgoin.loginGetPostByJersey(posturl, postparm);
		System.out.println("Cookie as Token paramter=" + cook);
		  
		logPassStatus("User successfully logged into Command Center");

	}
	@Test(priority =2)
	public void addNewBusinessService(){
		
				
		@SuppressWarnings("resource")
		FormDataMultiPart formData = new FormDataMultiPart();
		formData.field("url", "https://www.gmail.com");
		formData.field("name", "TestNow");
		formData.field("group", "TestNow");
		formData.field("sub_group", "TestNow");
		formData.field("email_Id", "test@test.com");
		formData.field("interval", "Minutes");
		formData.field("repeat_every", "2");
		
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: AddNewBusinessService");

		String addNewBusinessServiceUrl = testdatamap.get("addNewBusinessServiceUrl");
		System.out.println("addNewBusinessServiceUrl=" + addNewBusinessServiceUrl);
		
		String url = testdatamap.get("url");
		System.out.println("url=" + url);
		String name = testdatamap.get("name");
		System.out.println("name==" + name);
		String group = testdatamap.get("group");
		System.out.println("group==" + group);
		String sub_group = testdatamap.get("sub_group");
		System.out.println("sub_group==" + sub_group);
		String email_Id = testdatamap.get("email_Id");
		System.out.println("email_Id==" + email_Id);
		String interval = testdatamap.get("interval");
		System.out.println("interval==" + interval);
		int repeat_every =Integer.parseInt( testdatamap.get("repeat_every"));
		System.out.println("repeat_every==" + repeat_every);
		
		
		String postparam = "{\"url\":\"" + url + "\",\"name\":\"" + name + "\",\"group\":\"" + group + "\",\"sub_group\":\"" + sub_group + "\",\"email_Id\":\"" + email_Id + "\",\"interval\":\"" + interval + "\",\"repeat_every\":2 }";
		logPassStatus("New Businesss Service has been added successfully");
			
		System.out.println(postparam);
		
		
		Post postcall = new Post();

		response = postcall.getPostByJersey(addNewBusinessServiceUrl, postparam,cook);
		System.out.println(response);
		
	}

	
	
    public void logPassStatus(String statusMsg) {
		test.log(LogStatus.PASS, statusMsg);

	}

	@AfterClass
	public void tear() {
		extent.endTest(test);
		extent.flush();
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
}
