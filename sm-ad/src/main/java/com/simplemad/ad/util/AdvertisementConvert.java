package com.simplemad.ad.util;

import com.simplemad.ad.domain.AdvertisementEffect;
import com.simplemad.base.util.StringUtil;
import com.simplemad.bean.Advertisement;
import com.simplemad.bean.AdvertisementType;

public class AdvertisementConvert {

	public static Advertisement convert(com.simplemad.ad.domain.Advertisement ad, AdvertisementEffect effect) {
		Advertisement beanAd = new Advertisement();
		beanAd.setId(ad.getId().toString());
		beanAd.setAdType(ad.getAdType());
		beanAd.setName(ad.getName());
		beanAd.setMessage(true);
		beanAd.setMobile(effect.getUser().getMobileNo());
		beanAd.setPreviewFileExtendedName(ad.getIconExtendedName());
		beanAd.setFileExtendedName(ad.getContentExtendedName());
		beanAd.setStartDate(effect.getStartDate());
		beanAd.setEndDate(effect.getEndDate());
		beanAd.setPrice(effect.getMoney().getUserAdMoney());
		beanAd.setSharable(effect.isSharable());
		/**设置广告获取金钱按钮出现的延迟时间*/
		if(ad.getAdType().equals(AdvertisementType.HTML)) {
			beanAd.setWaitingTime(10);
		} else if(ad.getAdType().equals(AdvertisementType.TEXT) || ad.getAdType().equals(AdvertisementType.IMAGE)) {
			beanAd.setWaitingTime(5);
		}
		if(effect.isSharable()) {
			beanAd.setSharingPrice(effect.getMoney().getUserSharingMoney());
		} else {
			beanAd.setSharingPrice(0);
		}
		beanAd.setUrl(addMobileNumToUrl(ad.getUrl(), effect.getUser().getMobileNo(), effect.getUser().getPassword(), ad.getAdType()));
		return beanAd;
	}
	
	private static String addMobileNumToUrl(String url, long mobile, String password, AdvertisementType adType) {
		if(StringUtil.isEmpty(url)) {
			return null;
		}
		if(AdvertisementType.INTERACTION == adType) {
			StringBuffer urlBuffer = new StringBuffer();
			urlBuffer.append(url);
			if(url.contains("?")) {
				urlBuffer.append("&").append("mobile=").append(mobile);
				urlBuffer.append("&").append("password=").append(password);
			} else {
				urlBuffer.append("?").append("mobile=").append(mobile);
				urlBuffer.append("&").append("password=").append(password);
			}
			return urlBuffer.toString();
		} else {
			return url;
		}
	}
}
