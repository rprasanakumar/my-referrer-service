package org.service.referrer.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.service.referrer.model.ReferrerURL;

import database.DataStoreMap;

public class ReferrerServiceImplementationStub implements IReferrerService {

	ReferrerURL referrer =null;
	private Map<Long,ReferrerURL> urlCollection = DataStoreMap.getURLMap(); 
	public ReferrerServiceImplementationStub( ReferrerURL referrer){
		
		this.referrer =referrer;
	}
	public ReferrerServiceImplementationStub(){
		
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
		}
		
	}
	
	public ReferrerURL putDataURL(){
		
		try{
			long key = 0;
			String str =null;
			long id = urlCollection.size()+1;
			extractDomainUrl();
			str= referrer.getDomain();
			for(long k: urlCollection.keySet() ){
				if(urlCollection.get(k).getDomain().equals(str)){
					key = k;
					break;
				}
			}
		
			if(key==0){
				referrer.setId(id);
				referrer.setHitCount(1);
				DataStoreMap.insertDataURL(id, referrer);
				
			}
			else{
				referrer.setId(key);
				DataStoreMap.updateDataCount(key);
				
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return referrer;
	}
	
	public List<ReferrerURL> getTopURL(){
		PriorityQueue<ReferrerURL> queue = new PriorityQueue<ReferrerURL>(3); 
		List<ReferrerURL> lst = new ArrayList<ReferrerURL>();
		for(long id: urlCollection.keySet()){
			if(queue.size()<3){
				queue.add(urlCollection.get(id));
			}
			else{
			if(urlCollection.get(id).getHitCount()>queue.peek().getHitCount()){
				queue.poll();
				queue.add(urlCollection.get(id));
			}
			}
		}
		lst.addAll(queue);
		return lst;
	}

}
