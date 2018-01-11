package com.wusc.campaign.service;

import com.wusc.campaign.pojo.AccountToken;
import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * create by wusc on 2018/1/9
 */
@Service
public class TokenService {
    private static final Logger log  = LoggerFactory.getLogger(TokenService.class);
    @Value("${token.tokenTTL}")
    private long tokenTTL;
    @Value("${token.refreshTTL}")
    private long refreshTTL;
    @Value("${token.exists}")
    private long exists;
    @Autowired
    private RedisTemplate redisTemplate;

    public ReturnResult login(AccountToken accountToken){

        if(redisTemplate.keys(accountToken.getId()+":*").size()>=exists){
            return ResultUtil.BEYOND_LOGIN_TIMES;
        }
        String token =accountToken.getId()+":"+ UUID.randomUUID().toString();
        accountToken.setTokenTTL(System.currentTimeMillis()+tokenTTL);
        accountToken.setRefreshTTL(System.currentTimeMillis()+refreshTTL);
        try{
            redisTemplate.opsForValue().set(token,accountToken,refreshTTL,TimeUnit.SECONDS);
        }catch (Exception e){
            log.error("login redis set error",e);
            return ResultUtil.LOGIN_ERROR;
        }
        Map<String,String> data=new HashMap<>();
        data.put("token",token);
        return new ReturnResult(ResultUtil.SUCCESS_CODE,data);
    }

    public ReturnResult logout(String token) {
        try{
            redisTemplate.delete(token);
        }catch (Exception e){
            log.error("logout delete error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    public ReturnResult refresh(String oldToken) {
        AccountToken accountToken=(AccountToken)redisTemplate.opsForValue().get(oldToken);
        redisTemplate.delete(oldToken);
       return this.login(accountToken);
    }
}
