package com.wusc.entrancebase.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
	
	private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

	public static long toLong(String str) {
		return toLong(str, 0);
	}
	
	public static long toLong(String str, long def) {
		str = trim(str);
		if (str == null || str.length() == 0) {
			return def;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return def;
		}
	}
	
	public static int toInt(String str) {
		return toInt(str, 0);
	}
	
	public static int toInt(String str, int def) {
		str = trim(str);
		if (str == null || str.length() == 0) {
			return def;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return def;
		}
	}
	
	public static String trim(String source) {
		if (source == null) {
			return null;
		}
		return source.trim();
	}
	
	public static String toLogSafeString(Object source) {
		if (source == null) {
			return "";
		}
		if (source instanceof Boolean) {
			if (source.equals(Boolean.TRUE)) {
				return "1";
			} else {
				return "0";
			}
		}
		char[] chars = source.toString().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case '\t':
			case '\r':
			case '\n':
				chars[i] = ' ';
				break;
			default:
				break;
			}
		}
		return new String(chars);
	}
}
