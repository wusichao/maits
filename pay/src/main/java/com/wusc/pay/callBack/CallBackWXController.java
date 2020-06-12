package com.wusc.pay.callBack;

import com.objcoding.wxpay.sdk.WXPay;
import com.objcoding.wxpay.sdk.WXPayUtil;
import com.wusc.pay.service.PayService;
import com.wusc.vo.ReturnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * create by wusc on 2018/1/22
 */
@RestController
public class CallBackWXController {
    private static final Logger logger = LoggerFactory.getLogger(CallBackWXController.class);
    @Autowired
    private PayService payService;
    @Autowired
    private WXPay wxPay;
    private static final String SUCCESS = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<xml>\n" +
            "<return_msg>OK</return_msg>\n" +
            "<return_code>SUCCESS</return_code>\n" +
            "</xml>";
    private static final String FAIL = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<xml>\n" +
            "<return_code>FAIL</return_code>\n" +
            "</xml>";

    @RequestMapping(value = "call_back_wx", method = RequestMethod.POST)
    public String callback(HttpServletRequest request) {
        logger.info("enter call_back_wx");
        Map<String, String> mapResult = new HashMap<>();
        Map<String, String> mapParams = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        String xmlString = null;
        StringBuffer inputString = new StringBuffer();

        try {
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlString = inputString.toString();
        try {
            request.getReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(xmlString);
        try {
            mapParams = WXPayUtil.xmlToMap(xmlString);
            if (checkOrderStatus(mapParams.get("out_trade_no"))) {
                return SUCCESS;
            }

            if (wxPay.isPayResultNotifySignatureValid(mapParams)) {
                ReturnResult returnResult = payService.updateOrder(mapParams.get("out_trade_no"), mapParams.get("transaction_id"));
                if (returnResult.getCode() != 200) {
                    //TODO 业务出错，记录
                    return FAIL;
                }
                return SUCCESS;
            } else {
                return FAIL;
            }
        } catch (Exception e) {
            logger.error("callback error", e);
            return FAIL;
        }
    }

    private boolean checkOrderStatus(String orderId) {

        return payService.checkOrderStatus(orderId);
    }

}
