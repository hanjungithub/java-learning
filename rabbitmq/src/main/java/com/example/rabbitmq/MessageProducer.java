package com.example.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MessageProducer
 * @Description 消息生产者
 * @Author hanjun
 * @Date 2019/9/2 15:10
 **/
@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static int i =0;

    /**
     * 生产简单队列消息，多个消费者接收
     */
    public void sendMessage() {
            Message message = new Message();
            message.setContent(String.valueOf(Math.random()));
            message.setTime(new Date());
            rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_A, message);
        }

    /**
     * 生产消息，往direct交换机上发送消息
     */
    public void sendMessageToDirectExchange(){
        Message message = new Message();
        message.setContent("direct-Message:"+String.valueOf(Math.random()));
        message.setTime(new Date());
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_DIRECT,RabbitmqConfig.EXCHANGE_EDIRECT_KEY,message);
    }

    /**
     * 生产消息，往fanout交换机上发送消息
     */
    public void sendMessageToFanoutExchange(){
        Message message = new Message();
        message.setContent("fanout-Message:"+String.valueOf(Math.random()));
        message.setTime(new Date());
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_FANOUT,"",message);
    }

    /**
     * 生产消息，往topic交换机上发送消息
     */
    public void sendMessageToTopicExchange(){
        Message message = new Message();
        message.setContent("topic-message1:"+i++);
        message.setTime(new Date());
        Message message1 = new Message();
        message1.setContent("topic-message2:"+String.valueOf(Math.random()));
        message1.setTime(new Date());
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPIC,"EXCHANGE_TOPIC.MESSAGE1",message);
        //rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPIC,"EXCHANGE_TOPIC.MESSAGE2",message1);
    }

}
