package org.service.referrer.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javax.management.RuntimeErrorException;

import org.service.referrer.ErrorList;
import org.service.referrer.dao.ReferrerDAO;
import org.service.referrer.model.ReferrerURL;
import org.service.referrer.model.TopReferrerUrl;
import org.service.referrer.utilities.CacheSerializationUtility;

import database.DataStoreMap;
/**
 * @author Prasanna Kumar Rajendran
 * Core service implmention class
 * with method putDataURL and getTopURL implemented
 */


public class ReferrerServiceImplementation implements IReferrerService {

	final String SER_PATH = "/cache/topurl.ser"; 
	ReferrerURL referrer =null;
	private PriorityQueue<ReferrerURL> qeue;
	public ReferrerServiceImplementation( ReferrerURL referrer){
		
		this.referrer =referrer;
	}
	public ReferrerServiceImplementation(){
		
	}
// extracts the url's domain only
	public void extractDomainUrl() throws URISyntaxException {
		
		URI uri ;
		String urlDomain =null;
		String url = referrer.getDomain();
		if(url!=null && !url.isEmpty()){
			uri = new URI(url);
			urlDomain = uri.getHost();
			if (urlDomain!=null){
				urlDomain = urlDomain.startsWith("www.")? urlDomain.substring(4):urlDomain;
			}
			referrer.setDomain(urlDomain);
			referrer.setId(Math.abs(urlDomain.hashCode()));
		}
		else{
			throw new RuntimeException(ErrorList.ERROR_0003);
		}
		
	}
	
	// Inserts the url domain, id and count into the data base
	public ReferrerURL putDataURL(){
		ReferrerDAO rfrDAO = new ReferrerDAO();
		try{
			extractDomainUrl();
			rfrDAO.insertDataURL(referrer);
			referrer =rfrDAO.populateUrl(referrer);
			cacheTopUrl();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return referrer;
	}
	
	// Returns the Priority Queue as a list
	public List<ReferrerURL> getTopURL() {
		List<ReferrerURL> lst = new ArrayList<ReferrerURL>();
		TopReferrerUrl urlObject = (TopReferrerUrl) CacheSerializationUtility.deserialize(SER_PATH);
		if(urlObject!=null){
			qeue = urlObject.getQueue();
			lst.addAll(qeue);			
		}
		
		return lst;
	}
	
	/**
	 * Cache the present topurls into as a serialized Min Heap
	 *  1. when there is a incoming request to persist a new referrer URL, 
	 *  2. checks its hit count with the top element in the min heap
	 *  3  ignore the new url if the count is less than the top else replace new url with the top element in the Heap
	 */
	public void cacheTopUrl(){
		TopReferrerUrl urlObject = (TopReferrerUrl) CacheSerializationUtility.deserialize(SER_PATH);
		boolean isPresent =false;
		if(urlObject!=null){
			qeue = urlObject.getQueue();
			
			for(ReferrerURL ref:qeue){
				if(ref.getId()==referrer.getId()){
					isPresent=true;
					//ref.setHitCount(Long.MIN_VALUE);
					qeue.remove(ref);
					break;
				}
			}
			if(!isPresent){
				
				if(qeue.size()<3){
					qeue.add(referrer);
				}
				else if(qeue.peek().getHitCount()< referrer.getHitCount()){
					qeue.poll();
					qeue.add(referrer);
					
				}
				
			}else{
				qeue.add(referrer);	
			}
			
			
		}
		else{
			urlObject = new TopReferrerUrl();
			urlObject.addToQueue(referrer);
			
		}
		CacheSerializationUtility.serialize(SER_PATH, urlObject);
		
	}
	

}
