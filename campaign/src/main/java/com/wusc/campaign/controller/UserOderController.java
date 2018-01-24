package com.wusc.campaign.controller;

import com.wusc.campaign.service.UserOrderService;
import com.wusc.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by wusc on 2018/1/19
 */
@RestController
public class UserOderController {
    @Autowired
    private UserOrderService userOderService;

    @ApiOperation(value="account register", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "order",method = RequestMethod.POST)
    public ReturnResult order(@RequestParam(required =true) long id){

        return userOderService.add(id);
    }
}
