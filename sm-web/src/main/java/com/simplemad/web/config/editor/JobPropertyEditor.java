package com.simplemad.web.config.editor;

import org.springframework.util.StringUtils;

import com.simplemad.base.domain.enums.Job;

public class JobPropertyEditor extends SimplemadPropertyEditor {

	@Override
	public Class<?> getEntityClass() {
		return Job.class;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Job.valueOf(text));
		}
	}

	public String getAsText() {
		Job job = (Job) getValue();
		return (job != null ? job.getName() : "");
	}

}
