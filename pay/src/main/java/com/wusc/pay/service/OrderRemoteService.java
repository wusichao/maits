package com.wusc.pay.service;

import com.wusc.vo.ReturnResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by wusc on 2018/1/25
 */
@FeignClient(name = "order", fallback =OrderRemoteServiceFail.class )
public interface OrderRemoteService {

    @RequestMapping(value = "order",method = RequestMethod.PUT)
    public ReturnResult orderBack(@RequestParam(required =true) String id);
}
