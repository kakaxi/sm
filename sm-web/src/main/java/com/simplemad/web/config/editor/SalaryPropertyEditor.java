package com.simplemad.web.config.editor;

import org.springframework.util.StringUtils;

import com.simplemad.base.domain.enums.Salary;

public class SalaryPropertyEditor extends SimplemadPropertyEditor {

	@Override
	public Class<?> getEntityClass() {
		return Salary.class;
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Salary.valueOf(text));
		}
	}

	public String getAsText() {
		Salary salary = (Salary) getValue();
		return (salary != null ? salary.getName() : "");
	}

}
