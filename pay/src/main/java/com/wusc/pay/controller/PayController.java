package com.wusc.pay.controller;

import com.wusc.pay.service.PayService;
import com.wusc.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by wusc on 2018/1/15
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @ApiOperation(value="account register", response=ReturnResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType="String", paramType="header", value="令牌", name="token", required=true),
    })
    @RequestMapping(value = "order/{order}",method = RequestMethod.GET)
    public byte[] unifiedorder(@PathVariable(required = true) String order){
        return payService.unifiedorder(order);
    }


}
