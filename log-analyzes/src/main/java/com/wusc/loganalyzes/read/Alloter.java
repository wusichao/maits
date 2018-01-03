package com.wusc.loganalyzes.read;

import com.wusc.loganalyzes.analyzes.BaseReport;
import com.wusc.loganalyzes.analyzes.LogAnalyzer;
import com.wusc.loganalyzes.analyzes.LogField;
import com.wusc.loganalyzes.dao.ReportDao;
import com.wusc.loganalyzes.analyzes.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * create by wusc on 2017/12/27
 */
@Component
public class Alloter {
    private static final Logger logger= LoggerFactory.getLogger(Alloter.class);
    @Autowired
    private LogAnalyzer logAnalyzer;
    @Autowired
    private ReportDao reportDao;

    public void allot(File log){
        Map<LogField,BaseReport> baseReportMap=new HashMap<>();
        logger.debug("enter allot log:{}",log.getName());
        try(BufferedReader br = new BufferedReader(new FileReader(log))){
            String line;
            while((line = br.readLine()) != null){
                logger.debug("read line:{}",line);
                logAnalyzer.analyzes(line,baseReportMap);
            }
            reportDao.save(new ArrayList<BaseReport>(baseReportMap.values()));
            br.close();
        } catch ( IOException e) {
            logger.error("read file [{}] with exception.", new StringBuilder(log.getAbsolutePath()).append(File.separatorChar).append(log.getName()));
        }
    }
}