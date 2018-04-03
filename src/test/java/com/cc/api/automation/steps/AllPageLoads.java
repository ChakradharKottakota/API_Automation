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

public class AllPageLoads {
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
		  
		logPassStatus("login to Application with Userid=relevance/relevance Password=Relevance@123");

	}
	@Test(priority =2)
	public void cc_dashboard() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: Load Dashboard Page");

		String dashboardurl = testdatamap.get("dashboardurl");
		System.out.println("Dashboard Url=" + dashboardurl);
		logPassStatus("Dashboard page loaded");
		
		Get getcall =new Get();
		response = getcall.getRestServiceMethod(dashboardurl, cook);
		System.out.println(response);
	}
	@Test(priority =3)
	public void cc_alert() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: Load Alert Page");

		String alerturl = testdatamap.get("alerturl");
		System.out.println("Alert Url=" + alerturl);
		logPassStatus("Alert page loaded");
		
		Get getcall =new Get();
		response = getcall.getRestServiceMethod(alerturl, cook);
		System.out.println(response);
	}
	@Test(priority =4)
	public void cc_cmdb() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: Load Cloud CMDB Page");

		String cmdburl = testdatamap.get("cmdburl");
		System.out.println("CMDB Url=" + cmdburl);
		logPassStatus("Cloud CMDB page loaded");
		
		Get getcall =new Get();
		response = getcall.getRestServiceMethod(cmdburl, cook);
		System.out.println(response);
	}
	@Test(priority =5)
	public void cc_itsm() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: Load ITSM-ServiceNow Page");

		String itsmurl = testdatamap.get("itsmurl");
		System.out.println("ITSM Url=" + itsmurl);
		logPassStatus("ITSM-ServiceNow page loaded");
		
		Get getcall =new Get();
		response = getcall.getRestServiceMethod(itsmurl, cook);
		System.out.println(response);
	}
	@Test(priority =6)
	public void cc_services() {
		testdatamap = FilloExcelUtility.readExcel();
		test = ExtentManager.loggerInstance(extent, " Test case: Load Services Page");

		String servicesurl = testdatamap.get("servicesurl");
		System.out.println("Services Url=" + servicesurl);
		logPassStatus("Services page loaded");
		
		Get getcall =new Get();
		response = getcall.getRestServiceMethod(servicesurl, cook);
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
