package com.simplemad.base.domain.enums;

public enum Job {

	NONE("无业"),STUDENT("学生"),WORKER("工人"),OFFICE_WORKER("上班族"),
	CIVIL_SERVICE("公务员"),BOSS("企业主"), RETIREMENT("退休");
	private String name;
	
	private Job(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Job find(String name) {
		for(Job job : Job.values()) {
			if(job.getName().equals(name)) {
				return job;
			}
		}
		return null;
	}
}
