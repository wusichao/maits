package com.wusc.entrancebase.accumulators;

import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.model.params.CommonParam;
import com.wusc.entrancebase.servlet.ServletReqResp;
import com.wusc.entrancebase.utils.ServletUtils;
import com.wusc.entrancebase.utils.XidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class CommonAccumulator implements AccumulatorI{
    private final static Logger logger = LoggerFactory.getLogger(CommonAccumulator.class);

    @Override
    public void collect(ServletReqResp servletReqResp, Action action) {
        CommonParam param=null;
        try {
         param=collectParam(servletReqResp);
        }catch (IllegalArgumentException e){
         return;
        }
        collectAction(param,action);
    }

    private void collectAction(CommonParam param,Action action) {
        BeanUtils.copyProperties(param,action);
        action.setActionId(XidUtils.generateUuid());
        action.setIp(ServletUtils.getRemoteAddr(param.getRequest()));
        action.setSessionId(XidUtils.generateSessionId());
        action.setRequestTime(System.currentTimeMillis());
    }

    /**
     *
     * @param servletReqResp
     * @return
     */
    private CommonParam collectParam(ServletReqResp servletReqResp){
        HttpServletRequest req=servletReqResp.getRequest();
        String accountId = req.getParameter("a");
        String creativeId = req.getParameter("e");
        String campaignId = req.getParameter("c");
        String channelId = req.getParameter("h");
        String mediaId = req.getParameter("m");
        CommonParam params = new CommonParam();
        if (StringUtils.isEmpty(accountId) || StringUtils.isEmpty(creativeId) || StringUtils.isEmpty(campaignId) || StringUtils.isEmpty(channelId) || StringUtils.isEmpty(mediaId)) {
            logger.info("参数解析错误");
            throw new IllegalArgumentException();
        }else {//验证canpaign有效性

        }
        params.setAccountId(accountId);
        params.setCampaignId(campaignId);
        params.setChannelId(channelId);
        params.setCreativeId(creativeId);
        params.setMediaId(mediaId);
        params.setRequest(servletReqResp.getRequest());
        params.setResponse(servletReqResp.getResponse());
        return params;
    }

}
