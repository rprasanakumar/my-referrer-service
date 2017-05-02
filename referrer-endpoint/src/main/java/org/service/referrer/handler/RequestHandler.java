package org.service.referrer.handler;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.service.referrer.model.ReferrerURL;
import org.service.referrer.service.IReferrerService;
import org.service.referrer.service.ReferrerServiceImplementation;

import com.mysql.fabric.Response;



/** 
 * this Class is the request handler for different request that hits the referrer service 
 * actions  
 * @author Prasanna Kumar
 * @version 0.0.1
 * @param  referrer object
 */


@Path("/referrer")
public class RequestHandler {
	IReferrerService service = null;
	
	
	/**
	 * 
	 * @post request handler method with
	 * @param ReferrerURL object
	 *
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/url")
	public ReferrerURL postUrl(ReferrerURL referrer){
		
		try {
			service = new ReferrerServiceImplementation(referrer);
			return service.putDataURL();
		} catch (URISyntaxException e) {
			e.printStackTrace();	
		}
		return referrer;
	}
	
	
	/**
	 * 
	 * @get request handler method which
	 * @returns ReferrerURL list objects
	 *
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/top")
	public List<ReferrerURL> getTopUrl(){
		
		try {
			service = new ReferrerServiceImplementation();
			return service.getTopURL();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
