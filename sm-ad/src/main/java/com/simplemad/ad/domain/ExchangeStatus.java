package com.simplemad.ad.domain;

public enum ExchangeStatus {

	APPLIED("已申请"), PROCESSING("充值进行中"), COMPLETED("充值完成");
	
	private ExchangeStatus(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
}
