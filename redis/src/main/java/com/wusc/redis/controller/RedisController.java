package com.wusc.redis.controller;

import com.wusc.vo.ReturnResult;
import com.wusc.redis.dto.RedisDTO;
import com.wusc.redis.service.RedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces="application/json")
public class RedisController {
    @Autowired
    private RedisService service;
    @ApiOperation(value="redis操作", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "operate",method = RequestMethod.POST)
    public Object operate (@RequestBody RedisDTO param){

        return service.operate(param);
    }
}
