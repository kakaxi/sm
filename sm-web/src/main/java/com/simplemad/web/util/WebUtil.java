package com.simplemad.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

public class WebUtil extends WebUtils{

	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}
	
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static String[] getParameterValues(String parameterName) {
		return getHttpServletRequest().getParameterValues(parameterName);
	}
	
	public static String getParameterValue(String parameterName) {
		return getHttpServletRequest().getParameter(parameterName);
	}
	
	public static Object getAttribute(String attributeName) {
		return getHttpServletRequest().getAttribute(attributeName);
	}
	
	public static Object getObjectInSession(String key) {
		return getHttpSession().getAttribute(key);
	}
}
