package com.simplemad.base.domain.enums;

public enum Marriage {

	SINGLE("单身"),IN_LOVE("热恋"),MARRIED("已婚"), DIVORCED("离异");
	
	private String name;
	
	private Marriage(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Marriage find(String name) {
		for(Marriage marriage : Marriage.values()) {
			if(marriage.getName().equals(name)) {
				return marriage;
			}
		}
		return null;
	}
}
