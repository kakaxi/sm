package com.simplemad.base.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity
public class Area extends BaseEntity {

	private static final long serialVersionUID = -4215869904912511001L;
	
	private String name;
	
	@Reference(lazy = true)
	private City city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
