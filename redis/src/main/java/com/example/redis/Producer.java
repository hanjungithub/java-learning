package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author hanjun
 * @Date 2019/8/30 15:21
 **/
@Service
public class Producer {

    @Autowired
    private RedisTemplate redisTemplate;

    public void produce(){
        Message message = new Message();
        message.setContent(String.valueOf(Math.random()));
        message.setTime(new Date());
        redisTemplate.convertAndSend("message_1",message);
        redisTemplate.opsForList().leftPush("message_2",message);
    }

}
