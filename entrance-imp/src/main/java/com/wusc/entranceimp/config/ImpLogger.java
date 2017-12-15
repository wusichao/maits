package com.wusc.entranceimp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImpLogger {
    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger("imp.log");
    }


}
