package com.simplemad.base.domain.enums;

public enum Degree {
	NONE("无"),
	NURSERY_SCHOOL("幼儿园"),
	PRIMARY_SCHOOL("小学"), JUNIOR_SCHOOL("初中"), SENIOR_SCHOOL("高中"),
	SECONDARY_SCHOLL("中专"), VOCATIONAL_UNIVERSITY("大专"), UNIVERSITY("本科"), MASTER("硕士"),
	DOCTOR("博士");
	
	private String name;
	
	private Degree(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Degree find(String name) {
		for(Degree degree : Degree.values()) {
			if(degree.getName().equals(name)) {
				return degree;
			}
		}
		return null;
	}
}
