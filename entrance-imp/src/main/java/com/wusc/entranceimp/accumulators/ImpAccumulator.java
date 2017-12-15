package com.wusc.entranceimp.accumulators;

import com.wusc.entrancebase.accumulators.CommonAccumulator;
import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.servlet.ServletReqResp;
import com.wusc.entranceimp.model.params.ImpParam;
import org.springframework.stereotype.Component;
@Component
public class ImpAccumulator extends CommonAccumulator{
    @Override
    public void collect(ServletReqResp servletReqResp, Action action) {
        super.collect(servletReqResp,action);
        ImpParam param=collectParam(servletReqResp);
        collectAction(param,action);
    }

    private void collectAction(ImpParam param, Action action) {
    }

    /**
     *
     * @param servletReqResp
     * @return
     */
    private ImpParam collectParam(ServletReqResp servletReqResp) {
        ImpParam params = new ImpParam();
        return params;
    }
}
