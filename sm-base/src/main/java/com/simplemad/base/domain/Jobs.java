package com.simplemad.base.domain;

import com.simplemad.base.domain.enums.Job;

@Deprecated
public class Jobs extends BaseEntity {

	private static final long serialVersionUID = -1403417922404062168L;
	
	private Job job;
	
	private String key;
	
	private String value;
	
	private Area area;
	
	private String address;

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
