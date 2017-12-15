/**
 * 
 */
package com.wusc.entrancebase.utils;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XidUtils {

	private final static Logger logger = LoggerFactory.getLogger(XidUtils.class);

	private final static char[] ENCODE = { 'M','z','3','T','D','A','s','i','E','r','8','Z','F','W','j','G','l','Y','0','V','t','P','k','7','Q','b','q',
			'4','J','m','a','K','U','p','n','L','2','C','o','N','d','9','c','B','h','O','6','g','1','v','S','f','H','u','R','5','I','e','w','x','y','X'};

	private static String serverNum = "1";
	private final static String PROPS_FILE_NAME = "xid.properties";
	private final static String PROPS_SERVER_NUM_KEY = "server.num";

	static {
		
		try { 
			InputStream inputStream = XidUtils.class.getClassLoader().getResourceAsStream(PROPS_FILE_NAME); 
	        Properties p = new Properties();  
            p.load(inputStream);  
            inputStream.close();
            String s=p.getProperty(PROPS_SERVER_NUM_KEY);
            if(s!=null && s.trim().length()!=0)
            	serverNum=s;
            logger.info("read \"xid.properties\" successfully;"+"serverNum="+serverNum);
        } catch (Exception e) {  
        	logger.warn("read \"xid.properties\" failed;"+"serverNum="+serverNum); 
        }
	}
	
	private static AtomicLong userIdLastValue = new AtomicLong();

	public static String generateUserId() {
		
		return generateId(userIdLastValue);
	}

	private static AtomicLong sessionIdLastValue = new AtomicLong(123456);

	public static String generateSessionId() {
		
		return generateId(sessionIdLastValue);
	}

	private static AtomicLong uuidLastValue = new AtomicLong(234567);

	public static String generateUuid() {
		
		return generateId(uuidLastValue);
	}
	
	private static String generateId(AtomicLong lastValue) {
		Calendar c = Calendar.getInstance();
		StringBuilder sb = new StringBuilder(12);
		sb.append(ENCODE[c.get(Calendar.HOUR_OF_DAY)]);
		sb.append(ENCODE[c.get(Calendar.MINUTE)]);
		sb.append(ENCODE[c.get(Calendar.SECOND)]);
		int millisec = c.get(Calendar.MILLISECOND);
		sb.append(ENCODE[millisec / 62]);
		sb.append(ENCODE[millisec % 62]);

		sb.append(ENCODE[c.get(Calendar.YEAR) - 2000]);
		sb.append(ENCODE[c.get(Calendar.MONTH) + 1]);
		sb.append(ENCODE[c.get(Calendar.DAY_OF_MONTH)]);

		long value = Math.abs(lastValue.incrementAndGet() % 238328); 
																			
		sb.append(ENCODE[(int) (value / 3844)]); 
		value = value % 3844;
		sb.append(ENCODE[(int) (value / 62)]);
		sb.append(ENCODE[(int) (value % 62)]);
		sb.append(serverNum);
		return sb.toString();
	}

	public static String getServerNum() {
		return serverNum;
	}
	
	public static void main(String[] args){
		System.out.println(generateUuid());
		System.out.println(generateUserId());
	}
}
