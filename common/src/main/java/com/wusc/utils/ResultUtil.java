package com.wusc.utils;

import com.wusc.vo.ReturnResult;

public class ResultUtil {
	public static final int SUCCESS_CODE=200;

	public static final ReturnResult SUCEESS_NONE_DATA = new ReturnResult(SUCCESS_CODE, "");
	public static final ReturnResult SYSTEM_ERROR = new ReturnResult(999, "system error");
	public static final ReturnResult PARAM_ERROR = new ReturnResult(100, "param error");
	public static final ReturnResult REMOTE_FAIL=new ReturnResult(102,"serverbusy.htm");
	public static final ReturnResult VEVIFICATION_ERROR = new ReturnResult(101, "验证码错误或已过期");
	public static final ReturnResult UNKNOWN_PHONE_ERROR = new ReturnResult(201, "手机号未注册");
	public static final ReturnResult INVALID_PHONE_ERROR = new ReturnResult(202, "手机号格式有误");
	public static final ReturnResult REGED_PHONE_ERROR = new ReturnResult(203, "手机号已被注册");
	public static final ReturnResult LOGIN_ERROR = new ReturnResult(210, "用户名或密码错误");
	public static final ReturnResult NO_LOGIN = new ReturnResult(400, "not login");
	public static final ReturnResult TOKEN_EXPIRED = new ReturnResult(405, "token is expired");
	public static final ReturnResult NO_AUTH_ERROR = new ReturnResult(401, "no premission");
	public static final ReturnResult BEYOND_LOGIN_TIMES = new ReturnResult(402, "beyond allow login times");
}
