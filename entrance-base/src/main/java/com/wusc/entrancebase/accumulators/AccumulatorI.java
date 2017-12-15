package com.wusc.entrancebase.accumulators;

import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.servlet.ServletReqResp;

import javax.servlet.http.HttpServletRequest;

public interface AccumulatorI {
    void collect(ServletReqResp servletReqResp, Action action);
}
