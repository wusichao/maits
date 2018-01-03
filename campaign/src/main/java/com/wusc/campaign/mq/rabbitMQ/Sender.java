package com.wusc.campaign.mq.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * create by wusc on 2017/12/25
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate template;

    public void send(String key,Object message){
        this.template.convertAndSend(key,message);
    }
}
