package com.cc.api.automation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;





public class Configurations {

	private String browsertype = null;

	public String getBrowserName() {
		browsertype = getProperty("BrowserName");
		return browsertype;
	}

	public String getLibraryPath() {
		browsertype = getProperty("LibraryFolder");
		return browsertype;
	}

	public String getProperty(String Property) {
		String propertyValue = null;
		Properties properties = new Properties();
		InputStream is = Configurations.class
				.getResourceAsStream("E:BackUp/New API Auto/CommandCenter/DriverConfigurations.properties");
		System.out.println(is);
		try {
			properties.load(is);
			propertyValue = properties.getProperty(Property);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find the properties file " + e.toString());
		}
		return propertyValue;
	}
	
	
	public String getPropertyfromServices(String Property) {
		String propertyValue = null;
		Properties properties = new Properties();
		InputStream is = Configurations.class
				.getResourceAsStream("/services.properties");
		try {
			properties.load(is);
			propertyValue = properties.getProperty(Property);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find the properties file " + e.toString());
		}
		return propertyValue;
	}
	
	
	
	
	
	
	
	
	
	

	public void setProperty(String key, String value) {
		InputStream is = Configurations.class
				.getResourceAsStream("/DriverConfigurations.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find the properties file " + e.toString());
		}

		try {
			properties.setProperty(key, value);
			File f = new File("/DriverConfigurations.properties");
			OutputStream out = new FileOutputStream(f);
			properties.store(out, "Temporary properties for widget details");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not set the properties  " + e.toString());
		}
	}
	
	public static void main(String args[])
	{
		Configurations config=new Configurations();
		
		String fileLoc=config.getProperty("excelLoc");
		System.out.println("fileLoc="+fileLoc);
	}

	
}
