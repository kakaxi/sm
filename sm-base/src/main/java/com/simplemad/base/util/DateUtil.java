package com.simplemad.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class);

	public static String DEFAULT_PATTERN = "yyyy-MM-dd";

	public static String FULL_PATTERN = "yyyy-MM-dd hh:mm:ss";

	public static String YEAR_PATTERN = "yyyy";

	public static String translateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (null == date)
			return "";
		if (StringUtil.isEmptyOrWhitespaceOnly(pattern))
			sdf.applyPattern(DEFAULT_PATTERN);
		else
			sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	public static String translateToStringDefault(Date date) {
		return translateToString(date, null);
	}

	private static Date translateToDateLocal(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (StringUtil.isEmptyOrWhitespaceOnly(dateStr))
			return null;
		if (StringUtil.isEmptyOrWhitespaceOnly(pattern))
			sdf.applyPattern(DEFAULT_PATTERN);
		else
			sdf.applyPattern(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			log.error(String.format("The given date string [%s] can not be translate to Date type in the pattern %s!", dateStr, pattern));
		}
		return null;
	}

	public static Date translateToDateDefault(String dateStr) {
		return translateToDate(dateStr, null);
	}
	
	/**
	 * for the PinlogWebArgumentResolver in the web project 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date translateToDate (String dateStr, String pattern) {
		Date result = null;
		if(StringUtil.isEmptyOrWhitespaceOnly(dateStr))
			return result;
		if(!StringUtil.isEmptyOrWhitespaceOnly(pattern))
			result = translateToDateLocal(dateStr, pattern);
		if(null == result)
			result = translateToDateLocal(dateStr, DEFAULT_PATTERN);
		if(result == null)
			result = translateToDateLocal(dateStr, FULL_PATTERN);
		if(result == null)
			result = translateToDateLocal(dateStr, YEAR_PATTERN);
		return result;
	}
	
	public static Date addDay(Date first, int days) {
		if(first == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(first);
		c.add(Calendar.DAY_OF_YEAR, days);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.setTimeInMillis(c.getTimeInMillis() + 8 * 60 * 60 * 1000);
		System.out.println(translateToString(c.getTime(), FULL_PATTERN));
//		System.out.println(translateToString(date, FULL_PATTERN));
//		System.out.println(translateToString(addDay(date, 3), FULL_PATTERN));
	}
}
