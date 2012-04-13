package com.simplemad.web.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.simplemad.web.util.WebUtil;

/**
 * @author Kamen web项目的基类controller，其中包含了controller所需要的基本信息
 */
public abstract class BaseController {

	protected static final String USER = "user";

	protected HttpSession getHttpSession() {
		return WebUtil.getHttpSession();
	}

	protected HttpServletRequest getHttpServletRequest() {
		return WebUtil.getHttpServletRequest();
	}

	protected String[] getParameterValues(String parameterName) {
		return WebUtil.getParameterValues(parameterName);
	}

	protected String getParameterValue(String parameterName) {
		return WebUtil.getParameterValue(parameterName);
	}

	protected Object getAttribute(String attributeName) {
		return WebUtil.getAttribute(attributeName);
	}

	protected Object getObjectInSession(String key) {
		return WebUtil.getObjectInSession(key);
	}
	
	protected void setObjectInSession(String key, Object obj) {
		getHttpSession().setAttribute(key, obj);
	}

	protected void resetSession() {
		getHttpSession().invalidate();
	}

	@ModelAttribute("path")
	protected String getContextPath() {
		return getHttpServletRequest().getContextPath();
	}
}
