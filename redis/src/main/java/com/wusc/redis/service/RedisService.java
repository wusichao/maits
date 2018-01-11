package com.wusc.redis.service;

import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import com.wusc.redis.dto.RedisDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;

@Service
public class RedisService {
    private final static Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private JedisPool jedisPool;

    public Object operate(RedisDTO wapper){
        Jedis jedis =null;
        Object re=null;
        try {
            jedis=jedisPool.getResource();
            Method method =Jedis.class.getMethod(wapper.getMethod(),wapper.getClazzs());
            re= method.invoke(jedis,wapper.getParams());
        }catch(Exception e){
            logger.error("redis operate failure",e);
            return ResultUtil.SYSTEM_ERROR;
    }finally{
            if (jedis != null) jedis.close();
        }
        return new ReturnResult(ResultUtil.SUCCESS_CODE,re);
    }
}
