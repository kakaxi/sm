package com.simplemad.base.domain.enums;

public enum Have {

	HAVE("有"), HAVE_NOT("无");
	
	private String name;
	
	private Have(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Have find(String name) {
		for(Have have : Have.values()) {
			if(have.getName().equals(name)) {
				return have;
			}
		}
		return null;
	}
}
