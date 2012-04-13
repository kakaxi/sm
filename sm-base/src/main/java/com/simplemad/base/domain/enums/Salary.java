package com.simplemad.base.domain.enums;

public enum Salary {
	Salary1("无"), Salary2("0-1000"), Salary3("1000-3000"),
	Salary4("3000-6000"), Salary5("6000-10000"), Salary6("10000-30000"),
	Salary7("30000以上");
	
	private String name;
	
	private Salary(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Salary findEnumName(String enumName) {
		return Salary.valueOf(enumName);
	}
	
	public static Salary findByName(String name){
		for(Salary salary : Salary.values()) {
			if(salary.getName().equals(name)) {
				return salary;
			}
		}
		return Salary1;
	}
}
