package com.example.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName Job
 * @Description 定时生产
 * @Author hanjun
 * @Date 2019/8/30 15:26
 **/
@Component
public class Job {
    @Autowired
    private MessageProducer producer;

    @Scheduled(fixedRate = 10000)
    public void create() {
            producer.sendMessageToTopicExchange();
    }

}
