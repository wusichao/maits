package com.wusc.entranceclick.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClickLogger {
    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger("click.log");
    }


}
