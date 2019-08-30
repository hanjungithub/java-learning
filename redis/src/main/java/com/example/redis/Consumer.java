package com.example.redis;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author hanjun
 * @Date 2019/8/30 15:21
 **/
@Service
public class Consumer {
    public void receiveMessage(Object message){
        //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
        Jackson2JsonRedisSerializer seria = new Jackson2JsonRedisSerializer(Message.class);
        Message result = (Message)message;
        System.out.println("receiveMessage{ }:" + result);
       /* ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        seria.setObjectMapper(objectMapper);
        Message result = (Message)seria.deserialize(message.getBytes());
        System.out.println("receiveMessage{ }:" + result);*/
    }


}
