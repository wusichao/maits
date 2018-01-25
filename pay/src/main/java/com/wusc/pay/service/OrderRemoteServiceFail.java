package com.wusc.pay.service;

import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import org.springframework.stereotype.Service;

/**
 * create by wusc on 2018/1/25
 */
@Service
public class OrderRemoteServiceFail implements OrderRemoteService{

    @Override
    public ReturnResult orderBack(String id) {
        return ResultUtil.SYSTEM_ERROR;
    }
}
