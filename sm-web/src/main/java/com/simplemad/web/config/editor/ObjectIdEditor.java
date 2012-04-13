package com.simplemad.web.config.editor;

import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

public class ObjectIdEditor extends SimplemadPropertyEditor {

	@Override
	public Class<?> getEntityClass() {
		return ObjectId.class;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(new ObjectId(text));
		}
	}

	public String getAsText() {
		ObjectId id = (ObjectId) getValue();
		return (id != null ? id.toString() : "");
	}


}
