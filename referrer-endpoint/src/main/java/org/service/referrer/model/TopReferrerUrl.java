package org.service.referrer.model;

import java.io.Serializable;
import java.util.PriorityQueue;
/*
 * @author 	Prasanna Kumar Rajendran
 * @version 1.0
 * @date 04/24/2017
 * @serializable object which holds the
 * @param PriorityQueue with top three trending ReferrerURL 
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
