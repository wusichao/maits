package com.wusc.campaign.service;

import com.wusc.campaign.dao.UserOrderMapper;
import com.wusc.campaign.model.UserOrder;
import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * create by wusc on 2018/1/19
 */
@Service
public class UserOrderService {
    private static final Logger logger = LoggerFactory.getLogger(UserOrderService.class);
    @Autowired
    @Value("${order.user.ttl}")
    private long userOrderTTL;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserOrderMapper userOrderMapper;
    public ReturnResult add(long id) {
        String orderId= UUID.randomUUID().toString().replace("-","");
        UserOrder userOrder = new UserOrder(orderId,1L,new Date());
        try{
            redisTemplate.opsForValue().set("order:user:"+orderId,userOrder,userOrderTTL, TimeUnit.SECONDS);
        }catch (Exception e){
            logger.error("add error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
            return ResultUtil.SUCEESS_NONE_DATA;
    }
}
