package com.wusc.entranceclick.accumulaters;

import com.wusc.entrancebase.accumulators.CommonAccumulator;
import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.servlet.ServletReqResp;
import com.wusc.entranceclick.model.inputers.ClickAction;
import com.wusc.entranceclick.model.params.ClickParam;
import org.springframework.stereotype.Component;

@Component
public class ClickAccumulator extends CommonAccumulator{
    @Override
    public void collect(ServletReqResp servletReqResp, Action action) {
        super.collect(servletReqResp,action);
        ClickParam param=collectParam(servletReqResp);
        collectAction(param,(ClickAction)action);
    }

    private void collectAction(ClickParam param, ClickAction action) {
        action.setPreActionId("preActionId");
    }

    /**
     *
     * @param servletReqResp
     * @return
     */
    private ClickParam collectParam(ServletReqResp servletReqResp) {
        ClickParam params = new ClickParam();
        return params;
    }
}
