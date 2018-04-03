package com.cc.api.automation.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.NewCookie;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cc.api.automation.extent.ExtentManager;
import com.cc.api.automation.libs.CertificateHandle;
import com.cc.api.automation.libs.FilloExcelUtility;
import com.cc.api.automation.libs.Get;
import com.cc.api.automation.libs.LoginPost;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jersey.api.client.ClientResponse;

public class DevTest {
	static NewCookie cook;
	LoginPost lgoin = new LoginPost();
	Get get = new Get();
	ExtentReports extent;
	static ExtentTest test;
	ClientResponse response = null;
	//FilloExcelUtility rl=null;
	 HashMap<String, LinkedHashMap<Integer, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();
	@BeforeClass
	public void initateExtentManager() {
		extent = ExtentManager.Instance();
		// rl=new FilloExcelUtility();
	}

	/*
	@Test(priority = 1)
	void readexcel()
	{
		
	//	File file=new File("F:\\auto\\GenesisUIAutomation\\src\\test\\resources\\files\\ConfigSheet.xlsx");
	//	HashMap<String, LinkedHashMap<Integer, List<?>>> loadExcelLines = ExcelReader.loadExcelLines(file);
	//	HashMap<String, LinkedHashMap<Integer, List<?>>> loadcolms= new HashMap<String, LinkedHashMap<Integer, List<?>>> loadExcelLines();
		
	//	System.out.println(loadExcelLines.getClass());
	//	System.out.println(loadExcelLines.get("Sheet"));
	}
	*/
	//@Test(priority = 1, dataProvider = "TestDataProvider")
	//public void loginAdminUser(Map<String, String> dataMap)
	
	@Test(priority = 1)
    public void login_to_application()  {
		HashMap<String, String> testdatamap =FilloExcelUtility.readExcel();
		test=ExtentManager.loggerInstance(extent, " Test case: validate Mpn id");
		
	try {
		CertificateHandle.IgnoreSSLClient();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String p = null;
	
	String posturl=testdatamap.get("devUrl");
	System.out.println("posturl="+posturl);
	String username=testdatamap.get("username");
	System.out.println("username="+username);
	String password=testdatamap.get("password");
	System.out.println("password="+password);
	String postparmdup="{\"username\":\"pm6.qa@mailinator.com\",\"'"+p+"'\":\"Pass@123\"}";

	String postparm="{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
	 cook=lgoin.loginGetPostByJersey(posturl, postparm);
	System.out.println("Cookie as Token paramter="+cook);
	//FilloExcelUtility.updateRow("login1", "dev");
	FilloExcelUtility.updateRow("login1", "dev");
	logPassStatus("login to Application with Userid=pm6.qa@mailinator.com Password=Pass@123");
	
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
if (result.getStatus() == ITestResult.FAILURE) 
{
	//test.log(LogStatus.FAIL,result.getThrowable()+ test.addScreenCapture(ExtentManager.CaprtureScreenshot(driver,result.getInstanceName())));
	test.log(LogStatus.FAIL,result.getThrowable());
}	
}
}
