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

public class ReferrerServiceImplementation implements IReferrerService {

	final String SER_PATH = "/cache/topurl.ser"; 
	ReferrerURL referrer =null;
	private PriorityQueue<ReferrerURL> qeue;
	public ReferrerServiceImplementation( ReferrerURL referrer){
		
		this.referrer =referrer;
	}
	public ReferrerServiceImplementation(){
		
	}

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
	public List<ReferrerURL> getTopURL() {
		List<ReferrerURL> lst = new ArrayList<ReferrerURL>();
		TopReferrerUrl urlObject = (TopReferrerUrl) CacheSerializationUtility.deserialize(SER_PATH);
		if(urlObject!=null){
			qeue = urlObject.getQueue();
			lst.addAll(qeue);			
		}
		
		return lst;
	}
	
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
	
//	public List<ReferrerURL> getTopURL(){
//		PriorityQueue<ReferrerURL> queue = new PriorityQueue<ReferrerURL>(3); 
//		List<ReferrerURL> lst = new ArrayList<ReferrerURL>();
//		for(long id: urlCollection.keySet()){
//			if(queue.size()<3){
//				queue.add(urlCollection.get(id));
//			}
//			else{
//			if(urlCollection.get(id).getHitCount()>queue.peek().getHitCount()){
//				queue.poll();
//				queue.add(urlCollection.get(id));
//			}
//			}
//		}
//		lst.addAll(queue);
//		return lst;
//	}

}
