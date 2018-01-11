package com.wusc.token.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wusc.token.JwtManeger;
import com.wusc.token.annotation.SignLogin;
import com.wusc.token.pojo.JWTCheckResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class AuthInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private JwtManeger jwt;
	private ObjectMapper objectMapper;
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			SignLogin login = hm.getMethodAnnotation(SignLogin.class);
			if (login != null) { //验证是不是登陆入口
				return true;
			}
		}
		String token=request.getHeader("Authorization");

		JWTCheckResult result = jwt.validateJWT(token);
		if(result.getStatusCode()==1000){
			return true;
		}else{
			response.setStatus(result.getStatusCode());
			return false;
		}
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
