package com.simplemad.base.domain.enums;

public enum CarType {
	
	SMART_CAR("小型车"),NEUTRAL_CAR("中性车"),LUXURY_CAR("豪华车"),
	MPV("MPV"),SUV("SUV"),RACING_CAR("跑车");
	
	private String name;
	
	private CarType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static CarType find(String name) {
		for(CarType carType : CarType.values()) {
			if(carType.getName().equals(name)) {
				return carType;
			}
		}
		return null;
	}
}
