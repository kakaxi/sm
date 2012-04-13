package com.simplemad.base.domain.enums;

public enum Gender {

	Male("男"), Female("女");
	
	private Gender(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public static Gender find(String name) {
		for(Gender gender : Gender.values()) {
			if(gender.getName().equals(name)) {
				return gender;
			}
		}
		return null;
	}
}
