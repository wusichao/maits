package com.wusc.redis.controller;

import com.wusc.redis.param.ParamWapper;
import com.wusc.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService service;
    @RequestMapping("aperate")
    public Object operate (ParamWapper param){

        return service.operate(param);
    }
}
