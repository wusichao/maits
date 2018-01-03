package com.wusc.entranceimp.config;

import com.wusc.entrancebase.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RabbitConfig.class)
public class ImpLogger {
    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger("imp.log");
    }


}
