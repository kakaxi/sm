package com.simplemad.web.config.editor;

import org.springframework.util.StringUtils;

import com.simplemad.base.domain.enums.Gender;

public class GenderPropertyEditor extends SimplemadPropertyEditor {

	@Override
	public Class<?> getEntityClass() {
		return Gender.class;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Gender.valueOf(text));
		}
	}

	public String getAsText() {
		Gender type = (Gender) getValue();
		return (type != null ? type.getName() : "");
	}


}
