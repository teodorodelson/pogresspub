package com.example.demo.Controllers;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Models.Batch;
import com.example.demo.Models.Spider;
import com.example.demo.Models.Spider.GradeCategories;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class SpiderController {
	
	@Autowired
	public RestTemplate restTemplate()  throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    //Override certification verification. Always return true.
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	    //Create SSLContext with null key  store and override.
	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    //Remember the NoopHostNameVerifier for nothrow  of alternate name SSLException
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	    //Set httpclient with custom ssl verification inside request factory.
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    //Return rest template with custom requestfactory.
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }
	
	

	 @GetMapping(value="/demo/sample/{batch}/{id}")
	    public Object getMethodName(@PathVariable String id,@PathVariable String batch) {
		 System.out.println("I am called");
	        String uri = "http://34.82.182.44:80/mock/evaluation/grades/reports/{params2}/spider/{params}";
	        
	        Map<String, String> anotherId = new HashMap<String, String>();
	        anotherId.put("params", id);
	        anotherId.put("params2", batch);
	        try{
	        //Grab the custom Rest Template.
	        RestTemplate obj = restTemplate();
	        //Map the received data (JSON) to Batch.class
	        //If fields not found from Batch, fields will be empty.
	        //If not able to cast a field, will throw!
	       // Spider[] fetched = obj.getForObject(uri, Spider[].class ,anotherId);
	        Object fetched = obj.getForObject(uri, Object.class ,anotherId);
	        //Spider List<Spider>fetched[] = obj.getForObject(uri, Spider[].class ,anotherId);
	        
	        System.out.println(fetched);
	     
	        
	        
//	        List<Spider> ok = Arrays.asList((Spider[])fetched);
//	       
//	        System.out.println(ok);
//	        ArrayList<Spider> newlyFetched = new ArrayList<Spider>(fetched);
//           for( int i =0 ; i< fetched.length; i++){
//	       	System.out.println(fetched[i]);
//	        }
   return fetched;
//	    return ok.get(0).spiderTraineeGrades; 
//		//return fetched[0].spiderTraineeGrades;
	    }catch(Exception exception){System.out.println(exception.getMessage()); }
	        return null;
	    }

	 
//	  @GetMapping(value="/demo/sample/{id}")
//	    public ResponseEntity<?> getMethodName(@PathVariable String id) {
//	        String uri = "http://34.82.182.44:80/mock/evaluation/grades/reports/TR-1000/spider/{params}";
//	        Map<String, String> anotherId = new HashMap<String, String>();
//	        anotherId.put("params", id);
////	        Map<String, String> associateAssignments = new HashMap<String, String>();
//	//    
////	        associateAssignments.put("params", id);
//	        try{
//	        //Grab the custom Rest Template.
//	        RestTemplate obj = restTemplate();
//	        //Map the received data (JSON) to Batch.class
//	        //If fields not found from Batch, fields will be empty.
//	        //If not able to cast a field, will throw!
//	        Spider fetched = obj.getForObject(uri, Spider.class ,anotherId);
//	        System.out.println(fetched);
//	        //Fetch the field nothing, which has alias employeeAssignments, currentlyin response (aka trainer)
//	        //Is an array, so get index (0) trainer, and cast
//	        //Unchecked. If not able to cast, will throw!
////	        HashMap<Object, Object> parsed= (HashMap<Object,Object>) fetched.nothing.get(0);
////	       HashMap<Object, Object> parsed2= (HashMap<Object,Object>) fetched.associateAssignments.get(0);
////	        
//
//	        //Example of getting field role of trainer (0) from map and cast to string.
//		    //(String) parsed.get("role")
//	        
//	        //Chan
////	       ResponseEntity<HashMap<Object, Object>> response= new ResponseEntity(parsed, HttpStatus.OK);
//	        ResponseEntity<HashMap<Object, Object>> response2= new ResponseEntity(fetched, HttpStatus.OK);
////	        ResponseEntity<HashMap<Object, Object>> response3= new ResponseEntity(parsed2, HttpStatus.OK);
//	        
//	    	return response2;
//	    }catch(Exception exception){System.out.println(exception.getMessage()); }
//	        return null;
//	    }

}
