package com.wusc.entranceimp.servlet;

import com.wusc.entrancebase.servlet.BaseServlert;
import com.wusc.entrancebase.servlet.ServletReqResp;
import com.wusc.entranceimp.accumulators.ImpAccumulator;
import com.wusc.entranceimp.model.inputers.ImpAction;
import com.wusc.entranceimp.writers.ImpWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/i")
public class ImpServlert extends BaseServlert{
    private static final Logger logger = LoggerFactory.getLogger(ImpServlert.class);
    @Autowired
    private ImpAccumulator accumulator;
    @Autowired
    private ImpWriter writer;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ImpAction action=new ImpAction();
        accumulator.collect(new ServletReqResp(request,response),action);
        StringBuilder buf=new StringBuilder();
        writer.write(action,buf);
    }


}
