package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author hanjun
 * @date
 **/
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("通过实现CommandLineRunner接口，在spring boot项目启动后打印参数");
        SingleConsumer singleConsumer = new SingleConsumer(redisTemplate);
        singleConsumer.start();*/
    }
}