package com.simplemad.web.config.editor;

import org.springframework.util.StringUtils;

import com.simplemad.bean.AdvertisementType;

public class AdvertisementTypePropertyEditor extends SimplemadPropertyEditor {

	@Override
	public Class<?> getEntityClass() {
		return AdvertisementType.class;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(AdvertisementType.find(Integer.valueOf(text)));
		}
	}

	public String getAsText() {
		AdvertisementType type = (AdvertisementType) getValue();
		return (type != null ? type.getChineseName() : "");
	}


}
