package com.simplemad.ad.domain;

import java.util.ArrayList;
import java.util.List;

import com.simplemad.bean.AdvertisementType;

public enum AdStandard {

	TextLessThan20(AdvertisementType.TEXT, "文字20字以内"),
	Image(AdvertisementType.IMAGE, "图片"),
	Video5s(AdvertisementType.VIDEO, "5s广告视频"),
	Video15s(AdvertisementType.VIDEO, "15s广告视频"),
	Video30s(AdvertisementType.VIDEO, "30s广告视频"),
	Html5p(AdvertisementType.HTML, "页面内容5P以内"),
	Html10p(AdvertisementType.HTML, "页面内容10P以内"),
	Interaction10p(AdvertisementType.INTERACTION, "调查项目10P以内"),
	Interaction20p(AdvertisementType.INTERACTION, "调查项目20P以内"),
	InternalFree(AdvertisementType.INTERACTION, "内部广告(免费版)"),
	InternalFee(AdvertisementType.INTERACTION, "内部广告(收费版)"),
	//以上需要跑发布流程;以下则不需要跑发布步骤,只要发布审核通过即可,注册时自动推送
	NewTaskFree(AdvertisementType.INTERACTION, "新手广告(免费版)"),
	NewTaskFee(AdvertisementType.INTERACTION, "新手广告(收费版)");
	
	private AdStandard(AdvertisementType adType, String name) {
		this.adType = adType;
		this.name = name;
	}
	
	private AdvertisementType adType;
	
	private String name;
	
	public AdvertisementType getAdType() {
		return adType;
	}
	
	public String getName() {
		return name;
	}
	
	public static List<AdStandard> find(AdvertisementType adType) {
		List<AdStandard> standards = new ArrayList<AdStandard>();
		for(AdStandard s : AdStandard.values()) {
			if(s.getAdType().equals(adType)) {
				standards.add(s);
			}
		}
		return standards;
	}
	
	public static AdStandard find(String name) {
		for(AdStandard s : AdStandard.values()) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
}
