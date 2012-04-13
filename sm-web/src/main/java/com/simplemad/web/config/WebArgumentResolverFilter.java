package com.simplemad.web.config;

import org.springframework.core.MethodParameter;

import com.simplemad.base.config.ArgumentEntity;

public class WebArgumentResolverFilter {

	public static boolean isResolver(MethodParameter methodParameter) {
		if(ArgumentEntity.class.isAssignableFrom(methodParameter.getParameterType()))
			return true;
		return false;
	}
}
