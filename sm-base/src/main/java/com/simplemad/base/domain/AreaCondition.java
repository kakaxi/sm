package com.simplemad.base.domain;

import java.io.Serializable;

public class AreaCondition implements Serializable{

	private static final long serialVersionUID = -4215869904912511001L;
	
	private String id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
