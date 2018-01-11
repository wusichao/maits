package com.wusc.campaign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wusc.campaign.model.Account;
import com.wusc.campaign.pojo.AccountToken;
import com.wusc.campaign.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * create by wusc on 2018/1/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void redisop(){

       /* AccountToken accountToken =new AccountToken();
        accountToken.setTokenTTL(100);
        stringRedisTemplate.opsForValue().set("redis1",accountToken,100, TimeUnit.SECONDS);*/

    }
    @Test
    public void readRedis() throws IOException {
        String value=stringRedisTemplate.opsForValue().get("144:46780e87-d69c-467f-9ec2-75abbc536f69");
        AccountToken accountToken=objectMapper.readValue(value, AccountToken.class);
    }
}
