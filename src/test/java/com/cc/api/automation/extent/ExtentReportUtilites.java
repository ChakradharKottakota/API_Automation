package com.cc.api.automation.extent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExtentReportUtilites {
	
	  InetAddress ip;
      String hostname;
	
	/**
	 * @author Crunchify.com
	 */
	 
	  public ExtentReportUtilites(){
		  try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          hostname = ip.getHostName();
      }
	 
	  
	  
	  public InetAddress hostIpAdrees()
	  {
		  return(ip);
	  }
	  
	  public String userName()
	  {
		  return(System.getProperty("user.name"));
	  }
	  
	  
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  public static void main(String[] args)
	  {
		  ExtentReportUtilites s=new ExtentReportUtilites();
	System.out.println(s.hostIpAdrees());	
	System.out.println(s.userName());
		
	  }

}
