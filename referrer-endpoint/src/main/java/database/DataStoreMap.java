package database;

import java.util.concurrent.ConcurrentHashMap;

import org.service.referrer.model.ReferrerURL;

/** 
 * 
 * This class is a Data Base mock Stub. 
 * @author Prasanna Kumar
 * @version 0.0.1
 * @param  key- hash value of the domain url
 * @param  referrer object
 */
public class DataStoreMap {
	
	private static ConcurrentHashMap<Long, ReferrerURL> urlToIdMap = new ConcurrentHashMap<Long, ReferrerURL>();
	//private static ConcurrentHashMap<Long, Long> idToCountMap = new ConcurrentHashMap<Long, Long>();
	
	public static void insertDataURL(Long key,ReferrerURL referrer){
		
		try{
			urlToIdMap.put(key, referrer);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
//public static void insertDataCount(Long key,Long count){
//		
//		try{
//			idToCountMap.put(key,count );
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
//	}

public static void updateDataCount(Long key){
	
	try{
		ReferrerURL ref =urlToIdMap.get(key); 
		ref.setHitCount(ref.getHitCount()+1);
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	
}

public static ConcurrentHashMap<Long, ReferrerURL> getURLMap(){
	
	return urlToIdMap;
	
}
}
