package com.wusc.apigateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.wusc.apigateway.pojo.AccountToken;
import com.wusc.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@Component
public class ApiGagewayFilter extends ZuulFilter{
	private static final Logger logger = LoggerFactory.getLogger(ApiGagewayFilter.class);
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Value("${gateway.allow.url}")
	private String[] allowUrl;
	private Set<String> allowSet =new HashSet<>();
	@PostConstruct
	public void init() {
		for (String str : allowUrl) {
			logger.info("allow url is :{}",str);
			allowSet.add(str);
		}
	}
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token=request.getHeader("token");
		String uri = request.getRequestURI();
		logger.info("visit uri is:{}",uri);
		if(allowSet.contains(uri)){
			return null;
		}
		if(StringUtils.isEmpty(token)){
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			try {
				ctx.setResponseBody(objectMapper.writeValueAsString(ResultUtil.NO_LOGIN));
			} catch (JsonProcessingException e) {
				logger.error("run error",e);
				ctx.setResponseBody("{'code':400,'data':'no login'}");
			}
			return null;
		}
		String value = stringRedisTemplate.opsForValue().get(token);
		if(value==null){
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			try {
				ctx.setResponseBody(objectMapper.writeValueAsString(ResultUtil.NO_LOGIN));
			} catch (JsonProcessingException e) {
				logger.error("run error",e);
				ctx.setResponseBody("{'code':400,'data':'no login'}");
			}
			return null;
		}
		AccountToken accountToken= null;
		try {
			accountToken = objectMapper.readValue(value,AccountToken.class);
		} catch (IOException e) {
			logger.error("run error",e);
			ctx.setResponseBody("{'code':999,'data':'system error'}");
		}
		if(accountToken.getTokenTTL()>System.currentTimeMillis()){
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			try {
				ctx.setResponseBody(objectMapper.writeValueAsString(ResultUtil.TOKEN_EXPIRED));
			} catch (JsonProcessingException e) {
				logger.error("run error",e);
				ctx.setResponseBody("{'code':405,'data':'token is expired'}");
			}
			return null;
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
