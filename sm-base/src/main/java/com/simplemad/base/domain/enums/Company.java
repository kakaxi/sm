package com.simplemad.base.domain.enums;

public enum Company {
	STATE_OWNED_ENTERPRISE("国有企业"),INSTITUTIONS("事业单位"),
	JOINT_VENTURE("合资企业"), FOREIGN_FUNDED_ENTERPRISE("外资企业"),
	PRIVATE_ENTERPRISE("民营企业"), PRIVATE_OWNED_ENTERPRISE("私营企业");
	
	private String name;
	
	private Company(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Company find(String name) {
		for(Company company : Company.values()) {
			if(company.getName().equals(name)) {
				return company;
			}
		}
		return null;
	}
}
