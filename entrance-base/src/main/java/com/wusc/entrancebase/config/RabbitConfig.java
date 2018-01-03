package com.wusc.entrancebase.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * create by wusc on 2017/12/25
 */
@Configuration
@ComponentScan("com.wusc.entrancebase")
public class RabbitConfig {
    @Bean
    public Queue accountAddQueue(){
        return new Queue("account.add",true);
    }

    @Bean
    public Queue accountUpdateQueue(){
        return new Queue("account.update",true);
    }

    @Bean
    public Queue accountDeleteQueue(){
        return new Queue("account.delete",true);
    }

}
