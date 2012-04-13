package com.simplemad.base.domain.enums;

public enum Investment {
	
	NATIONAL_DEBT("国债"),SAVINGS("储蓄"),STOCK("股票"),
	FUTURES("期货"),GOLD("黄金"),FOREIGH_EXCHANGE("外汇"),
	FUND("基金"),FINANCIAL_PRODUCTS("理财产品"),INSURANCE("保险");
	
	private String name;
	
	private Investment(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Investment find(String name) {
		for(Investment investment : Investment.values()) {
			if(investment.getName().equals(name)) {
				return investment;
			}
		}
		return null;
	}
}
