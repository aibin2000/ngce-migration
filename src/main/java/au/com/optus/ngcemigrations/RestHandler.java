package au.com.optus.ngcemigrations;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestHandler extends RESTWorkItemHandler {
	
	public RestHandler() {
	    super(); 
	    System.out.println("custom constructor is called");
	}
	
	public RestHandler(ClassLoader classLoader, String processId, String strategy) {
	    super(classLoader, processId, strategy ); 
	    System.out.println("custom constructor is called with exception handler " + processId );
	}

	
	public RestHandler(String userName, String pwd) {
	    super(userName, pwd); 
	    System.out.println("custom constructor is called with username and password");
	}
	
	public RestHandler(ClassLoader classLoader) {
	    super(classLoader); 
	    System.out.println("custom constructor is called with classloader");
	}
	

	// @Override
	/* protected HttpClient getHttpClient(Integer readTimeout, Integer connectTimeout) {
	     
	     System.out.println("invoked by custom rest workitem handler");

		 RequestConfig config = RequestConfig.custom()
                 .setSocketTimeout(readTimeout)
                 .setConnectTimeout(connectTimeout)
                 .setConnectionRequestTimeout(connectTimeout)
                 .build();

         HttpClientBuilder clientBuilder = HttpClientBuilder.create()
                 .setDefaultRequestConfig(config).setSSLHostnameVerifier( NoopHostnameVerifier.INSTANCE);
         return clientBuilder.build();
      

	 }
	 */
}