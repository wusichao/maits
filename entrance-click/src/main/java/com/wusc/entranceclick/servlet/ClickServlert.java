package com.wusc.entranceclick.servlet;

import com.wusc.entrancebase.servlet.BaseServlert;
import com.wusc.entrancebase.servlet.ServletReqResp;
import com.wusc.entranceclick.accumulaters.ClickAccumulator;
import com.wusc.entranceclick.model.inputers.ClickAction;
import com.wusc.entranceclick.writers.ClickWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/c")
public class ClickServlert extends BaseServlert{
    private static final Logger logger = LoggerFactory.getLogger(ClickServlert.class);
    @Autowired
    private ClickAccumulator accumulator;
    @Autowired
    private ClickWriter writer;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ClickAction action=new ClickAction();
        accumulator.collect(new ServletReqResp(request,response),action);
        StringBuilder buf=new StringBuilder();
        writer.write(action,buf);
    }


}
