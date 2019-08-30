package com.example.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @ClassName SingleConsumer
 * @Description 生产者消费者模式
 * @Author hanjun
 * @Date 2019/8/30 16:04
 **/
@Service
public class SingleConsumer extends Thread{
    private RedisTemplate redisTemplate;

    SingleConsumer(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object message = redisTemplate.opsForList().leftPop("message_2");
           /* Jackson2JsonRedisSerializer seria = new Jackson2JsonRedisSerializer(Message.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            seria.setObjectMapper(objectMapper);
            Message result = (Message) seria.deserialize(message.getBytes());*/
           if(message!=null) {
               System.out.println("生产者-消费者模式：" + message);
           }
        }
    }
}
