package com.example.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description redis发布订阅配置
 * @Author hanjun
 * @Date 2019/8/30 15:20
 **/
@Configuration
public class RedisConfig {

    /**
     * redis消息监听器容器
     *
     * @param connectionFactory
     * @param consumerListenerAdapter 消息订阅处理器
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter consumerListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //消息订阅处理器
        container.addMessageListener(consumerListenerAdapter, new PatternTopic("message_3"));
        //container.setTopicSerializer(this.jacksonSerializer());
        // 这里看redisTemplate有没有序列化key如果有的话，这里也要同样对topic的名称进行序列化，否则匹配不到
        return container;
    }

    /**
     * 消息订阅处理器,并指定处理方法
     *
     * @param consumer
     * @return
     */
    @Bean
    MessageListenerAdapter consumerListenerAdapter(Consumer consumer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer, "receiveMessage");
        //在这里设置序列化方式，在具体的类里不需要重新解析，直接强转就可以了
        messageListenerAdapter.setSerializer(this.jacksonSerializer());
        return messageListenerAdapter;
    }

    @Bean
    RedisTemplate redisTemplate(RedisTemplate redisTemplate) {
        //自定义序列化方式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = this.jacksonSerializer();
        //redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private Jackson2JsonRedisSerializer jacksonSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }


}
