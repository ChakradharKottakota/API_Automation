package com.cc.api.automation.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.NewCookie;

import com.cc.api.automation.libs.CertificateHandle;
import com.cc.api.automation.libs.Get;
import com.cc.api.automation.libs.LoginPost;
import com.cc.api.automation.libs.Post;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;



public class MyJersyTestEnv2 {
	public static void main(String[] args) throws Exception{
		
	try {
		CertificateHandle.IgnoreSSLClient();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	// jc.getPostByJersey("https://test.redington.market/api/v1/auth/signIn", loginParm);
	
	LoginPost lgoin=new LoginPost();
	
	//login method
	String posturl="https://test.redington.market/api/v1/auth/signIn";
	String postparm="{\"username\":\"pm6.qa@mailinator.com\",\"password\":\"Pass@123\"}";
	NewCookie cook=lgoin.loginGetPostByJersey(posturl, postparm);
	System.out.println("Cookie as Token paramter="+cook);
	
	//normal get call
	
	Get get=new Get();
	String posturl2= "https://test.redington.market/api/v1/partnerEntity/onboarding/metadata";
	get.getRestServiceMethod(posturl2, cook);
	
	//verfiy entity type
	//jc.getRestServiceMethod("https://test.redington.market/api/v1/partnerEntity/verifyMPN/991450",hMap);
	String posturl3="https://test.redington.market/api/v1/partnerEntity/verifyMPN/991450";
	get.getRestServiceMethod(posturl3, cook);
	
	String posturl4="https://test.redington.market/api/v1/partnerEntity";
	
	//multivalued form data call
	 File initialFile = new File("C:/Users/rle0517/Desktop/6.jpg");
	InputStream targetStream1 = new FileInputStream(initialFile);
    InputStream targetStream2 = new FileInputStream(initialFile);
    InputStream targetStream3 = new FileInputStream(initialFile);
    InputStream targetStream4 = new FileInputStream(initialFile);
    InputStream targetStream5 = new FileInputStream(initialFile);
    
    
    
    HashMap<String,InputStream> fileloc=new HashMap<String,InputStream>();  
	
    fileloc.put("f1", targetStream1);
    fileloc.put("f2", targetStream2);
    fileloc.put("f3", targetStream3);
    fileloc.put("f4", targetStream4);
    fileloc.put("f5", targetStream5);
    
    FormDataMultiPart formData = new FormDataMultiPart() ; 
    formData.bodyPart(new FileDataBodyPart("logo", initialFile));
	formData.bodyPart(new FileDataBodyPart("Trade License Copy with Partners Page",initialFile));
	formData.bodyPart(new FileDataBodyPart("MOA / Shareholding letter (whichever is applicable)", initialFile));
	formData.bodyPart(new FileDataBodyPart("Trade License Copy with Share Certificate", initialFile));
	formData.bodyPart(new FileDataBodyPart("Owner/Decision Maker/Authorized Signatory Passport Copy with Visa Page", initialFile));
	formData.field("tradeLicenseValidityFrom", "2017-11-04");
	formData.field("tradeLicenseValidityTo", "2017-11-26");
	formData.field("existingType", "New");
	formData.field("entityName", "Burhani Computers Trading LLC");
	formData.field("mpnId", "991450");
	formData.field("addressLine1", "sdsa666dsdsdadsd");
	formData.field("addressLine2", "sad777asdasdasdasdd");
	formData.field("addressLine3", "asd888asdasdas");
	formData.field("pinCode", "60254");
	formData.field("countryCode", "ARE");
	formData.field("region", "008");
	formData.field("city", "dubai");
	formData.field("contactMobile", "3424242424242");
	formData.field("phoneNumber", "2323232323232");
	formData.field("signingAuthorityName", "sddsrrdsdsd");
	formData.field("signingAuthorityEmail", "sssesss@fff.com");
	formData.field("financeApproval", "false");
   	formData.field("partnerSegment", "Advisor & Consulting Services");
	formData.field("fqdn", "www.dddrfrr3d.com");
	formData.field("tradeLicenseNo", "323400002222");
	formData.field("vatId", "434390002342");
	formData.field("termsAndConditions", "on");   
	
	
	Post postcall=new Post();
	postcall.postMultiValuedWithAttachments(posturl4, fileloc, formData, cook);
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
}