package com.simplemad.ad.domain;

public enum AdStatus {

	NEW("新增"), UPDATE("更新"), 
	AD_AUDIT_PASSED("广告审查通过"), AD_AUDIT_NON_PASSED("广告审查不通过"),
	AD_SUBMIT("广告发布提交"),
	AD_PUBLISH_AUDIT_PASSED("广告发布审核通过"), AD_PUBLISH_AUDIT_NON_PASSED("广告发布审核不通过"),
	DELETE("删除");
	
	private AdStatus(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public static AdStatus find(String name) {
		for(AdStatus s : AdStatus.values()) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
}
