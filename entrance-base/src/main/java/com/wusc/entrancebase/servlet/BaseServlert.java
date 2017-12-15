package com.wusc.entrancebase.servlet;

import com.wusc.entrancebase.accumulators.CommonAccumulator;
import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.writers.CommonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlert extends HttpServlet {
    /*private static final Logger logger = LoggerFactory.getLogger(BaseServlert.class);
    @Autowired
    private CommonAccumulator accumulator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Action action=accumulator.collect(request);
    }
*/

}
