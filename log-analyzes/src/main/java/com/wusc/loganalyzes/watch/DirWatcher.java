package com.wusc.loganalyzes.watch;

import com.wusc.loganalyzes.read.LogReadTask;
import com.wusc.loganalyzes.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * create by wusc on 2017/12/27
 */
@Component
@Scope("prototype")
public class DirWatcher implements Runnable{
    private static final Logger logger= LoggerFactory.getLogger(DirWatcher.class);
    private String watchDir;
    @Autowired
    private LogReadTask logReadTask;
    private boolean watchFile = true;
    private static ExecutorService executor= Executors.newFixedThreadPool(5);

    public void setWatchDir(String watchDir) {
        this.watchDir = watchDir;
    }
    @Override
    public void run() {
        WatchService watchService;
        try {
            logger.debug("watch dir is :{}",watchDir);
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(watchDir).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                // 如果未变化，则阻塞进程
                WatchKey key = watchService.poll(6, TimeUnit.SECONDS);
                if(key!=null){
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE
                                || event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            File f = FileUtils.getFile(watchDir, event.context().toString());
                            if (f != null && f.isFile() && watchFile) {
                                if(f.getName().endsWith(".log")){
                                    logger.info("find watch log:{}",f.getName());
                                    logReadTask.setLog(f);
                                    executor.execute(logReadTask);
                                }else{
                                    logger.warn("发现异常文件："+f.getName());
                                }
                            }

                        } else {
                            logger.warn("目录:{},文件:{},事件:{},个数:{}", watchDir, event.context().toString(), event.kind(),
                                    event.count());
                        }
                    }
                    if (!key.reset()) {
                        logger.error("wathkey失效，停止监控", watchDir);
                        break;
                    }

                }else{
                    if(!new File(watchDir).exists()){
                        logger.info("目录{}已经不存在，停止监控", watchDir);
                        break;
                    }
                }

            }
        } catch (IOException | InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }
}
