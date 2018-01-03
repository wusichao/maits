package com.wusc.loganalyzes.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * create by wusc on 2017/12/27
 */
@Component
@Scope("prototype")
public class LogReadTask implements Runnable{
    private static final Logger logger=LoggerFactory.getLogger(LogReadTask.class);
    private File log=null;
    @Autowired
    private Alloter alloter;

    public void setLog(File log) {
        this.log = log;
    }

    @Override
    public void run() {
        logger.info("start running logReadTask");
        try {
            if(log != null ){
                    alloter.allot(log);
            }else{
                logger.info("no read");
            }
        } catch (Exception e) {
            logger.error("read file with exception [{}]",e.getLocalizedMessage(),e);
        }
    }
}
