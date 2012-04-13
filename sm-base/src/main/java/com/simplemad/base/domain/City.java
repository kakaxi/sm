package com.simplemad.base.domain;

import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class City extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2692341850365183349L;
	
	private String name;
	
	@Reference(lazy=true)
	private Province province;
	
	@Reference(lazy=true)
	private List<Area> areas;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

}
