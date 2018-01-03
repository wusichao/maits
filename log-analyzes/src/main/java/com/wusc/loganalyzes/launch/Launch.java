package com.wusc.loganalyzes.launch;

import com.wusc.loganalyzes.utils.SpringUtil;
import com.wusc.loganalyzes.watch.DirWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * create by wusc on 2017/12/27
 */
@Component
public class Launch {
    private static final Logger logger= LoggerFactory.getLogger(Launch.class);
    @Value("#{'${log.home}'.split(',')}")
    private List<String> watchDirs;
    public void run(String[] args) {
        logger.debug("watchDirs size is :{}",watchDirs.size());
        for(String path:watchDirs) {
                DirWatcher dirWatcher = (DirWatcher)SpringUtil.getBean("dirWatcher");
                String dateDirName=new StringBuilder(path).append(File.separatorChar).append(new SimpleDateFormat("yyyyMMdd").format(new Date())).toString();
                File dateDir=new File(dateDirName);
                if(!dateDir.exists()){
                    logger.debug("watchDir notExist and create dir");
                    dateDir.mkdirs();
                }
            Thread thread = new Thread(dirWatcher);
                dirWatcher.setWatchDir(dateDirName);
               /* thread.setDaemon(true);*/
                thread.start();
        };

    }
}
