package com.wusc.entrancebase.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.wusc.entrancebase.servlet.ServletReqResp;
import org.springframework.util.StringUtils;

public class ServletUtils {


	/**
	 * 取得远程地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.trim().length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.contains(",")) {
			// 多个IP的情况
			String[] ips = StringUtils.split(ip,",");
			for (int i = 0; i < ips.length - 1; i++) {
				String iip = ips[i].trim();
				try {
					if (!IpUtils.isPrivateIp(iip)) {
						return iip;
					}
				} catch (Exception e) {
					// 无效IP，不处理
				}
			}
			return ips[ips.length - 1].trim();
		}
		return ip;
	}

	/**
	 * 获取浏览器UA
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("user-agent");
	}

	/**
	 * 取得页面URL
	 * 
	 * @param request
	 * @return
	 */
	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("referer");
	}

	/**
	 * 取得服务入口地址及参数
	 * 
	 * @param request
	 * @return
	 */
	public static String getServiceUri(HttpServletRequest request) {
		String queryString = request.getQueryString();
		if (queryString == null) {
			return request.getRequestURI();
		}
		return request.getRequestURI() + "?" + queryString;
	}
	
	/**
	 * 存储到cookie
	 * 
	 * @param key 键
	 * @param value 值
	 * @param expiry 过期时间，单位为秒
	 * @param response HttpServletResponse
	 */
	public static void setCookie(String key, String value, int expiry, ServletReqResp dist) {
		//P3P 解决iframe跨域访问cookie问题
		dist.getResponse().setHeader(
				"P3P",
				"CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(expiry);
		cookie.setDomain(getRootDomain(dist.getRequest()));
		cookie.setPath("/");
		dist.getResponse().addCookie(cookie);
	}

	/**
	 * 存储到cookie
	 * 
	 * @param key 键
	 * @param value 值
	 * @param response HttpServletResponse
	 */
	public static void setCookie(String key, String value, ServletReqResp dist) {
		setCookie(key, value, Constant.COOKIE_EXPIRE_SECOND, dist);
	}

	/**
	 * 从cookie中取值
	 * 
	 * @param key 键
	 * @param request HttpServletRequest
	 * @return cookie值，如没有，返回null
	 */
	public static String getCookie(String key, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 从cookie中取得userId
	 * 
	 * @param request HttpServletRequest
	 * @return userId，如没有，返回null
	 */
	public static String getUserIdFromCookie(HttpServletRequest request) {
		return getCookie(Constant.KEY_USER_ID, request);
	}


	/**
	 * 将userId设置到COOKIE
	 * 
	 * @param userId userId
	 * @param response HttpServletResponse
	 */
	public static void setUserIdToCookie(String userId, ServletReqResp dist) {
		setCookie(Constant.KEY_USER_ID, userId, dist);
	}

	/**
	 * 取得SessionID,如没有，则创建
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getSessionId(ServletReqResp dist) {
		String ret = getCookie(Constant.KEY_SESSIONID, dist.getRequest());
		if (ret == null) {
			ret = XidUtils.generateSessionId();
			setCookie(Constant.KEY_SESSIONID, ret, -1, dist);
		}
		return ret;
	}
	
	public static String getRootDomain(HttpServletRequest request) {
		Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.io)");
		Matcher matcher = pattern.matcher(request.getServerName().toLowerCase());
		while (matcher.find()) {
			return "."+matcher.group();
		}
		return Constant.DOMAIN;
	}
	
	public static String encode(String s){
		if(s==null)
			return "";
		try{
			return URLEncoder.encode(s, "utf-8");
		}catch(UnsupportedEncodingException e){
			return "";
		}
	}
	
	public static String decode(String s){
		if(s==null)
			return "";
		try{
			return URLDecoder.decode(s, "utf-8");
		}catch(UnsupportedEncodingException e){
			return "";
		}
	}

}
