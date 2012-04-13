package com.simplemad.base.domain.enums;

public enum BodyType {
	
	BALANCE("匀称"),SPORTS("运动"),FATTER("偏肥"),
	THINNER("偏瘦"),SECRECY("保密");
	
	private String name;
	
	private BodyType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static BodyType find(String name) {
		for(BodyType bodyType : BodyType.values()) {
			if(bodyType.getName().equals(name)) {
				return bodyType;
			}
		}
		return null;
	}
}
