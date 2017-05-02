package org.service.referrer;

import  org.junit.Assert.*;
import org.junit.Before;
import org.service.referrer.model.ReferrerURL;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

/**
 * @author Prasanna Kumar Rajendran
 * this Class is used to test the two endpoints designed in the referrer url project
 */
public class ReferrerpostURLTest{
	
	public static final String BASE_URI = "http://localhost:8081/referrer-endpoint/webapi/referrer/";
	public static ReferrerURL ref;
	public static StringEntity params;
	Map< String, String >json ;
	
	
	
	   @Before
	    public void beforeClass() throws Exception {
		   json= new HashMap< String, String >();
		   json.put("domain", "\"http:\\www.123.com\"");
	    }
	
	
	/**
	 * 
	 *  This test is to check the status code of the http post request for the postURL service
	 */
	@Test
	public void givenEndpointofpostURL_whenHTTPRequestIsMade_then200IsReceived()
	      throws ClientProtocolException, IOException{
	   // Given
		
		HttpPost request = new HttpPost(BASE_URI+"url");
	    request.addHeader("content-type", "application/json");
	    request.setEntity(params);		
		JSONObject jsonObject = new JSONObject(json);
		StringEntity entity = new StringEntity(jsonObject.toString(), "UTF8");
		entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		request.setEntity(entity);
	   
	   // When
	   HttpResponse httpResponse = new DefaultHttpClient().execute( request );
	   
	   // Then
	   assertEquals(Response.Status.OK.getStatusCode(), httpResponse.getStatusLine().getStatusCode());
	}
	

	   
}