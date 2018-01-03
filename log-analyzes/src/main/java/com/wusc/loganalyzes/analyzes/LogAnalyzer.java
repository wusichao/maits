package com.wusc.loganalyzes.analyzes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;

/**
 * create by wusc on 2017/12/27
 */
@Component
public class LogAnalyzer{
    private static final Logger logger= LoggerFactory.getLogger(LogAnalyzer.class);
    @Autowired
    private LineParser lineParser;

    public void analyzes(String line,Map<LogField,BaseReport> baseReport) {
        try {
            LogField logField=lineParser.parseLine(line);
            if(baseReport.containsKey(logField)){
                logger.debug("logs contains logField:{}",logField);
               updateBaseReport(logField,baseReport);
               logger.debug("update after baseReport:{}",baseReport.get(logField).toString());
            }else{
                logger.debug("first ada logFiled:{}",logField);
               addBaseReport(logField,baseReport);
                logger.debug("first add after baseReport:{}",baseReport.get(logField).toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addBaseReport(LogField logField, Map<LogField,BaseReport> baseReport) {
        BaseReport report = new BaseReport();
        logFieldChangeReport(logField,report);
        logger.debug("put key:{},value:{}",report,report);
        baseReport.put(logField,report);

    }

    private void logFieldChangeReport(LogField logField, BaseReport baseReport) {
        BeanUtils.copyProperties(logField,baseReport);
        switch (logField.getActionType()){
            case IMPRESSION:
                baseReport.setImp(1);
                break;
            case CLICK:
                baseReport.setClick(1);
                break;
        }
        logger.debug("logFeild copy baseReport baseReport:{}",baseReport);
    }

    private void updateBaseReport(LogField logField, Map<LogField,BaseReport> baseReport) {
        switch (logField.getActionType()){
            case IMPRESSION:
               baseReport.get(logField).impIncr();
                break;
            case CLICK:
                baseReport.get(logField).clickIncr();
                break;
        }
    }
}
