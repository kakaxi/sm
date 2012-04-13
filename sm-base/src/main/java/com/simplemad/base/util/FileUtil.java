package com.simplemad.base.util;

public class FileUtil {

	public static final String DOT = ".";
	public static String getFileExtendedName(String fileName) {
		if(StringUtil.isEmpty(fileName)) {
			return "";
		}
		int index = fileName.lastIndexOf(DOT);
		if(index == -1) {
			return "";
		} else if(index == fileName.length() - 1){
			return "";
		} else {
			return fileName.substring(index + 1);
		}
	}
}
