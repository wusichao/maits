package com.wusc.token;

public class Constant {

	/**
	 * 数据请求返回码
	 */
	public static final int JWT_VERIFY_SUCCESS = 1000;	
	public static final int JWT_TOKEN_EXPIRE = 1001;			
	public static final int JWT_TOKEN_INVALID = 1002;
	public static final int JWT_VERIFY_ERROR = 1003;	
	
	/**
	 * jwt
	 */
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
}
