package com.simplemad.base.domain.enums;

public enum DietTaste {
	
	HEAVY("偏重"), LIGHT("偏淡"),CHILLI("偏辣"),MUSLIM("清真"),NONE("无要求");
	
	private String name;
	
	private DietTaste(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static DietTaste find(String name) {
		for(DietTaste taste : DietTaste.values()) {
			if(taste.getName().equals(name)) {
				return taste;
			}
		}
		return null;
	}
}
