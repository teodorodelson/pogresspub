package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.example.demo.Models.BatchOwn;
import com.example.demo.Models.Employee;
import com.example.demo.Models.BatchOwn.Categories;;

@RestController
public class MyBatchController {


//Why?
//Self signed certificate can be added to cert store but name does't match host.
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
    
    @GetMapping(value="/demo/getGrade/{id}")
    public Categories getMethodName(@PathVariable String id) {
        String uri = "https://34.82.182.44:443/mock/evaluation/grades/reports/individual/{params}";
        Map<String, String> anotherId = new HashMap<String, String>();
        anotherId.put("params", id);
        try{
        //Grab the custom Rest Template.
        RestTemplate obj = restTemplate();
        //Map the received data (JSON) to Batch.class
        //If fields not found from Batch, fields will be empty.
        //If not able to cast a field, will throw!
        BatchOwn fetched = obj.getForObject(uri, BatchOwn.class ,anotherId);
        System.out.println(fetched);
        //Fetch the field nothing, which has alias employeeAssignments, currentlyin response (aka trainer)
        //Is an array, so get index (0) trainer, and cast
        //Unchecked. If not able to cast, will throw!
        //HashMap<Object, Object> parsed2= (HashMap<Object,Object>) fetched.traineeGrades;
        
        
        //Example of getting field role of trainer (0) from map and cast to string.
	    //(String) parsed.get("role")
        
        //Change
       // ResponseEntity<HashMap<Object, Object>> response= new ResponseEntity(parsed2, HttpStatus.OK);
        
        

   //	return new ResponseEntity<?>( fetched.traineeGrades);
       // System.out.println(fetched.traineeGrades);
	return fetched.traineeGrades;
    }catch(Exception exception){System.out.println(exception.getMessage()); }
        return null;
    }
    
}
