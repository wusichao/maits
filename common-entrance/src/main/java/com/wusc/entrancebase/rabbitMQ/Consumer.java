package com.wusc.entrancebase.rabbitMQ;

import com.wusc.entrancebase.cache.LocalCache;
import com.wusc.campaign.model.Account;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * create by wusc on 2017/12/25
 */
@Component
@RabbitListener(queues = "account.add")
public class Consumer {
    @Autowired
    private LocalCache cache;

    @RabbitHandler
    public void process(Account message){
        System.out.println(message.toString());
        cache.addAccount(message);
    }
}
