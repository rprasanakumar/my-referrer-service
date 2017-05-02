package org.service.referrer.model;

import java.io.Serializable;

import org.service.referrer.ErrorList;


/** 
 * this Class serializable plain old java object which holds the url's  
 * domain, id and hitcount  
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public class ReferrerURL implements Comparable<ReferrerURL>, Serializable {
	
	/**
	 * IMPORTANT
	 * Do not change this Version number unless there is a real need
	 * 
	 */
	private static final long serialVersionUID = -6305455888154930616L;
	private String domain;
	private long id;
	private Long hitCount;
	
	public Long getHitCount() {
		return hitCount;
	}
	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		if(domain!=null && !domain.isEmpty() && domain.length()<256)
			this.domain = domain;
		else
			throw new RuntimeException(ErrorList.ERROR_0003);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

	public int compareTo(ReferrerURL o) {
		return this.getHitCount().compareTo(o.getHitCount());
	}
	

	 @Override
	    public boolean equals(Object o) {
	        if ((o instanceof ReferrerURL) && (((ReferrerURL) o).getId() == this.getId())) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
	    @Override
	    public int hashCode() {
	        return (int) this.getId();
	    }



}
