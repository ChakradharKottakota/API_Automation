package com.cc.api.automation.libs;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.eclipse.jetty.io.ssl.ALPNProcessor.Client;

public class CertificateHandle {
	   public static Client IgnoreSSLClient() throws Exception {
		   SSLContext ctx = null;
           TrustManager[] trustAllCerts = new X509TrustManager[]{new X509TrustManager(){
               public X509Certificate[] getAcceptedIssuers(){return null;}
               public void checkClientTrusted(X509Certificate[] certs, String authType){}
               public void checkServerTrusted(X509Certificate[] certs, String authType){}
           }};
           try {
               ctx = SSLContext.getInstance("SSL");
               ctx.init(null, trustAllCerts, null);
           } catch (NoSuchAlgorithmException | KeyManagementException e) {
               //LOGGER.info("Error loading ssl context {}", e.getMessage());
           }

           SSLContext.setDefault(ctx);
		return null;
		}

}
