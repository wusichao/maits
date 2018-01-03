package com.wusc.auth.utils;

public class ResultUtil {
	public static final int SUCCESS_CODE=200;

	public static final ReturnResult SUCEESS_NONE_DATA = new ReturnResult(SUCCESS_CODE, "");
	public static final ReturnResult SYSTEM_ERROR = new ReturnResult(999, "系统异常");
	public static final ReturnResult PARAM_ERROR = new ReturnResult(100, "参数错误");
	public static final ReturnResult REMOTE_FAIL=new ReturnResult(102,"系统繁忙");
	public static final ReturnResult VEVIFICATION_ERROR = new ReturnResult(101, "验证码错误或已过期");
	public static final ReturnResult UNKNOWN_PHONE_ERROR = new ReturnResult(201, "手机号未注册");
	public static final ReturnResult INVALID_PHONE_ERROR = new ReturnResult(202, "手机号格式有误");
	public static final ReturnResult REGED_PHONE_ERROR = new ReturnResult(203, "手机号已被注册");
	public static final ReturnResult LOGIN_ERROR = new ReturnResult(210, "用户名或密码错误");
	
	public static final ReturnResult NO_AUTH_ERROR = new ReturnResult(401, "没有权限");
	public static final ReturnResult NO_DATA_ERROR = new ReturnResult(404, "查无数据");
}
