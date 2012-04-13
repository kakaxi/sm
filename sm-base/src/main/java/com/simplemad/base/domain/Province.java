package com.simplemad.base.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Province extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7244126429030374943L;
	
	private String name;
	
	@Reference(lazy=true)
	private List<City> cities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		if(cities == null)
			cities = new ArrayList<City>();
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
