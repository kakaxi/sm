package com.simplemad.ad.domain;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.simplemad.base.domain.Area;
import com.simplemad.base.domain.BaseEntity;

@Entity
public class Advertiser extends BaseEntity {

	private static final long serialVersionUID = -4389600502702529162L;
	
	private String name;
	
	private String address;
	
	private Area area;
	
	private Date registerDate;
	
	private Industry industry;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
}
