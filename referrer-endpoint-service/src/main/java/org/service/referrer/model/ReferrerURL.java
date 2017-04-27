package org.service.referrer.model;


/*
 * @author 	Prasanna Kumar Rajendran
 * @version 1.0
 * @date 04/24/2017
 */
public class ReferrerURL implements Comparable<ReferrerURL> {
	
	private String domain;
	private long id;
	private Long hitCount;
	
	public Long getHitCount() {
		return hitCount;
	}
	public void setHitCount(long hitCount) {
		this.hitCount = hitCount;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public int compareTo(ReferrerURL o) {
		// TODO Auto-generated method stub
		return this.getHitCount().compareTo(o.getHitCount());
	}


}
