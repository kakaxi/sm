package com.simplemad.base.domain.enums;

public enum PhoneAppHobby {
	
	GAME("游戏"),SNS("社交"),NEWS("新闻资讯"),
	APPTOOL("工具应用"),NOVEL("小说"),STUDY("学习");
	
	private String name;
	
	private PhoneAppHobby(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static PhoneAppHobby find(String name) {
		for(PhoneAppHobby hobby : PhoneAppHobby.values()) {
			if(hobby.getName().equals(name)) {
				return hobby;
			}
		}
		return null;
	}
}
