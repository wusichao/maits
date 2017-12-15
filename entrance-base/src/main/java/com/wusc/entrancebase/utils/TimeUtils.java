package com.wusc.entrancebase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
	
	private static final int offsetMillis = TimeZone.getDefault().getRawOffset();
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat sdfMills = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public static final SimpleDateFormat sdfSecs = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static final SimpleDateFormat sdfMinute = new SimpleDateFormat("yyyyMMddHHmm");

	public static int getDayIndex(long timeMillis){
		return (int)((timeMillis + offsetMillis)/86400000L);
	};
	
	
	public static long getTimeMillis(String dayStr) throws ParseException{
		return sdf.parse(dayStr).getTime();
	}
	
	public static int getDayIndex(String dayStr) throws ParseException{
		return getDayIndex(getTimeMillis(dayStr));
	}
	
	public static int getHourOfDay(long timestamp) {
		return (int) (((timestamp + offsetMillis) % 86400000L) / 3600000L);
	}
	
	public static String formatMills(long millis){
		return sdfMills.format(new Date(millis));
	}
	
	public static String formatSecs(long millis){
		return sdfSecs.format(new Date(millis));
	}
	
	public static long getMillsFromStr(String str){
		try {
			return sdfMills.parse(str).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
	
	public static Date getDateFromStr(String str){
		try {
			return sdfMills.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String getIntervalMinute(String currentMinute,int interval){
		try{
			Date currentDate=sdfMinute.parse(currentMinute);
			Calendar c = Calendar.getInstance();
	        c.setTime(currentDate);
	        c.add(Calendar.MINUTE, interval);
	        return sdfMinute.format(c.getTime());
		}catch(Exception e){
			return null;
		}
	}
}
