package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    private Producer producer;

    @Scheduled(fixedRate = 3000)
    public void create(){
        producer.produce();
    }

}
