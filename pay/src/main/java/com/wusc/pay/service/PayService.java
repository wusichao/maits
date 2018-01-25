package com.wusc.pay.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wxpay.sdk.WXPay;
import com.wusc.pay.dao.PayMapper;
import com.wusc.pay.model.Pay;
import com.wusc.pay.pojo.UserOrder;
import com.wusc.pay.utils.QRCodeUtil;
import com.wusc.utils.ResultUtil;
import com.wusc.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * create by wusc on 2018/1/15
 */
@Service
public class PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayService.class);
    @Autowired
    private WXPay wxPay;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    @Value("${pay.wx.spbillCreateIp}")
    private String spbillCreateIp;
    @Autowired
    @Value("${pay.wx.notifyUrl}")
    private String notifyUrl;
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private OrderRemoteService orderRemoteService;

    public byte[] unifiedorder(String order){
        UserOrder userOrder = null;
        Map<String,String> mapParam =new HashMap();
        Map<String,String> mapResult =new HashMap();
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        try {
            userOrder = objectMapper.readValue(stringRedisTemplate.opsForValue().get("order:user:"+order),UserOrder.class);
        } catch (IOException e) {
            logger.error("unifiedorder error",e);
        }
        try {
            mapParam.put("nonce_str",UUID.randomUUID().toString().replace("-",""));
            mapParam.put("body","test");
            mapParam.put("out_trade_no",userOrder.getId());
            mapParam.put("total_fee",userOrder.getPrice().toString());
            mapParam.put("spbill_create_ip",spbillCreateIp);
            mapParam.put("notify_url",notifyUrl);
            mapParam.put("trade_type","NATIVE");
            mapResult=wxPay.unifiedOrder(mapParam);
        } catch (Exception e) {
            logger.error("unifiedorder error",e);
        }

        try {
            QRCodeUtil.encode(mapResult.get("code_url"),output);
        } catch (Exception e) {
            logger.error("unifiedorder error",e);
        }
        Pay pay =new Pay();
        pay.setOrderId(userOrder.getId());
        payMapper.insert(pay);
        return output.toByteArray();
    }

    @Transactional
    public ReturnResult updateOrder(String out_trade_no, String transaction_id) {
        Pay pay =new Pay(transaction_id,1);
        Wrapper<Pay> wrapper = new EntityWrapper<>();
        if (out_trade_no!=null){
            wrapper.eq("order_id",out_trade_no);
        }
        try{
            //TODO need distrubuted transactional
            payMapper.update(pay,wrapper);
            ReturnResult result=orderRemoteService.orderBack(out_trade_no);
            if(result.getCode()!=200){
            throw new Exception("orderRemoteServiceFail");
            }
        }catch (Exception e){
            logger.error("updateOrder error",e);
            return ResultUtil.SYSTEM_ERROR;
        }
        return ResultUtil.SUCEESS_NONE_DATA;
    }

    public boolean checkOrderStatus(String orderId) {
        Pay pay = new Pay();
        pay.setOrderId(orderId);
        if(payMapper.selectOne(pay)==null){
            return false;
        }
        return true;
    }
}
