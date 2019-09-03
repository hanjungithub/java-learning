package com.example.rabbitmq;

import com.rabbitmq.client.AMQP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitmqConfig
 * @Description rabbitMQ的配置文件
 * @Author hanjun
 * @Date 2019/9/2 14:57
 **/
@Configuration
public class RabbitmqConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_DIRECT_A = "QUEUE_DIRECT_A";
    public static final String QUEUE_DIRECT_B = "QUEUE_DIRECT_B";
    public static final String QUEUE_TOPIC_A = "QUEUE_TOPIC_A";
    public static final String QUEUE_TOPIC_B = "QUEUE_TOPIC_B";
    public static final String EXCHANGE_FANOUT = "EXCHANGE_FANOUT";
    public static final String EXCHANGE_DIRECT = "EXCHANGE_DIRECT";
    public static final String EXCHANGE_TOPIC = "EXCHANGE_TOPIC";
    public static final String EXCHANGE_EDIRECT_KEY = "EXCHANGE_EDIRECT_KEY";
    public static final String EXCHANGE_TOPIC_KEY = "EXCHANGE_TOPIC.*";
    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                  //log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


   /* *//**
     * 定义一个普通队列A
     *
     * @return
     *//*
    @Bean
    public Queue queueA() {
        //队列持久
        return new Queue(QUEUE_A, true);
    }*/
}
