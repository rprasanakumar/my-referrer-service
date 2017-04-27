package org.service.referrer.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.service.referrer.dao.ReferrerDAO;
import org.service.referrer.model.ReferrerURL;

import database.DataStoreMap;

public class ReferrerServiceImplementation implements IReferrerService {

	ReferrerURL referrer =null;
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
		}
		
	}
	
	public ReferrerURL putDataURL(){
		ReferrerDAO rfrDAO = new ReferrerDAO();
		try{
			extractDomainUrl();
			rfrDAO.insertDataURL(referrer);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return referrer;
	}
	@Override
	public List<ReferrerURL> getTopURL() {
		// TODO Auto-generated method stub
		return null;
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
