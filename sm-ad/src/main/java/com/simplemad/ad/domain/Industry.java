package com.simplemad.ad.domain;

import com.google.code.morphia.annotations.Entity;
import com.simplemad.base.domain.BaseEntity;

@Entity
public class Industry extends BaseEntity {

	private static final long serialVersionUID = 6509163223479901675L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
