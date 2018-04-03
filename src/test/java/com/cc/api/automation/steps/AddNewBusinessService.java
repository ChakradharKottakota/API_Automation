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
		  
		logPassStatus("login to Application with Userid=relevance/relevance Password=Relevance@123");

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
		
		//cook = lgoin.loginGetPostByJersey(url, postparam);
		Post postcall = new Post();

		response = postcall.getPostByJersey(addNewBusinessServiceUrl, postparam,cook);
		System.out.println(response);
		/*System.out.println(cook);
		response =postcall.getPostByJersey(addNewBusinessServiceUrl, postparam);
		 System.out.println(response);*/
		/*Post postcall = new Post();

		response = postcall.(addNewBusinessServiceUrl+cook);
		
		
		
		// multivalued form data call
		//for windows
        /*Configurations config=new Configurations();
		String fileLoc=config.getProperty("jpegfile");
		System.out.println("fileLoc="+fileLoc);
		File initialFile = new File(fileLoc);
		
		
		//for linux
		//	File initialFile = new File("/var/lib/jenkins/jobs/Genesis_API_Automation/workspace/src/test/resources/files/6.jpg");
		InputStream targetStream1 = new FileInputStream(initialFile);
		InputStream targetStream2 = new FileInputStream(initialFile);
		InputStream targetStream3 = new FileInputStream(initialFile);
		InputStream targetStream4 = new FileInputStream(initialFile);
		InputStream targetStream5 = new FileInputStream(initialFile);
		HashMap<String, InputStream> fileloc = new HashMap<String, InputStream>();
		fileloc.put("f1", targetStream1);
		fileloc.put("f2", targetStream2);
		fileloc.put("f3", targetStream3);
		fileloc.put("f4", targetStream4);
		fileloc.put("f5", targetStream5);
		FormDataMultiPart formData = new FormDataMultiPart();
		//formData.bodyPart(new FileDataBodyPart("logo", initialFile));
		//formData.bodyPart(new FileDataBodyPart("Trade License Copy with Partners Page", initialFile));
		//formData.bodyPart(new FileDataBodyPart("MOA / Shareholding letter (whichever is applicable)", initialFile));
		//formData.bodyPart(new FileDataBodyPart("Trade License Copy with Share Certificate", initialFile));
		//formData.bodyPart(new FileDataBodyPart("Owner/Decision Maker/Authorized Signatory Passport Copy with Visa Page",
		//		initialFile));
		//formData.field("tradeLicenseValidityFrom", "2017-11-04");
		//formData.field("tradeLicenseValidityTo", "2018-11-26");
		//formData.field("existingType", "New");
		String entityName = testdatamap.get("entityName");
		System.out.println("entityName=" + entityName);
		formData.field("entityName", entityName);
		//String mpnId = testdatamap.get("mpnId");
		//formData.field("mpnId", mpnId);
		//formData.field("addressLine1", "addressLine1");
		//formData.field("addressLine2", "addressLine2");
		//formData.field("addressLine3", "addressLine3");

		formData.field("pinCode", "60254");
		formData.field("countryCode", "KWT");
		formData.field("region", "003");
		formData.field("city", "kuwait city");
		formData.field("contactMobile", "3424242424243");
		formData.field("phoneNumber", "2323232323233");
		formData.field("signingAuthorityName", "signingAuthority");
		formData.field("signingAuthorityEmail", "signingAuthority@fff.com");
		formData.field("financeApproval", "false");
		formData.field("partnerSegment", "Advisor & Consulting Services");
		FilloExcelUtility.updateRow("domainName", "AddNewPartnerOrganisationTest");
		testdatamap = FilloExcelUtility.readExcel();
		String domainName = testdatamap.get("domainName");

		domainName = "https://www." + domainName + ".com";
		System.out.println("Domain name=" + domainName);
		formData.field("fqdn", domainName);
		FilloExcelUtility.updateRow("tradeLicenseNo", "AddNewPartnerOrganisationTest");
		testdatamap = FilloExcelUtility.readExcel();
		String tradeLicenseNo = testdatamap.get("tradeLicenseNo");
		System.out.println("tradeLicenseNo=" + tradeLicenseNo);
		formData.field("tradeLicenseNo", tradeLicenseNo);
		FilloExcelUtility.updateRow("vatId", "AddNewPartnerOrganisationTest");
		testdatamap = FilloExcelUtility.readExcel();
		String vatId = testdatamap.get("vatId");
		System.out.println("vatId=" + vatId);
		formData.field("vatId", vatId);
		formData.field("termsAndConditions", "on");
		Post postcall = new Post();

		response = postcall.postMultiValuedWithAttachments(addNewBusinessServiceUrl, fileloc, formData, cook);
		// Assert.assertEquals("Partner organization with these details already exits in
		// the system",200 , response.getStatus());

		// System.out.println(response.toString());
		// logPassStatus("New Partner created "+"<br/>Response=" +response.toString());
		logPassStatus("New Partner created");
*/		
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