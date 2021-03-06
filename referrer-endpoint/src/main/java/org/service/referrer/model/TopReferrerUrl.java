package org.service.referrer.model;

import java.io.Serializable;
import java.util.PriorityQueue;

/** 
 * this Class has serializable PriorityQueue with top three trending ReferrerURL   
 * domain, id and hitcount  
 * @author Prasanna Kumar
 * @version 0.0.1
 * @param referrer object
 */
public class TopReferrerUrl implements Serializable {

	/**
	 * IMPORTANT
	 * Do not change this Version number unless there is a real need
	 * 
	 */
	private static final long serialVersionUID = 7072317745755164338L;

	
	private PriorityQueue<ReferrerURL> queue = new PriorityQueue<ReferrerURL>();
	
	public void addToQueue(ReferrerURL referrer){
		
		queue.add(referrer);
	}
	
	public PriorityQueue<ReferrerURL> getQueue(){
		
		return queue;
	}
	
	
	
}
