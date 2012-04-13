package com.simplemad.base.domain.enums;

public enum SpareHobby {
	
	HOME("宅"),OUTDOOR("户外"),TRAVEL("旅游"),
	ELECTRONIC_GAME("电子游戏"),SPORTS("运动"),
	READING("阅读"),SHOPPING("购物"),NIGHT_CLUB("逛夜店");
	
	private String name;
	
	private SpareHobby(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static SpareHobby find(String name) {
		for(SpareHobby hobby : SpareHobby.values()) {
			if(hobby.getName().equals(name)) {
				return hobby;
			}
		}
		return null;
	}
}
