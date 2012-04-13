package com.simplemad.base.util;

public class PojoUtil {
	
	private static final String SETTER = "set";
	
	private static final String GETTER = "get";

	public static String obtainSetterMethodName(String fieldName) {
		return obtainMethodName(SETTER, fieldName);
	}

	public static String obtainGetterMethodName(String fieldName) {
		return obtainMethodName(GETTER, fieldName);
	}
	
	private static String obtainMethodName(String header, String fieldName) {
		if(!StringUtil.hasText(fieldName))
			return null;
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		StringBuilder builder = new StringBuilder();
		builder.append(header);
		builder.append(firstLetter);
		builder.append(fieldName.substring(1));
		return builder.toString();
	}
}
