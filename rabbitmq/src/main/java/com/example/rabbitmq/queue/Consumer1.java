package com.example.rabbitmq.queue;

import com.example.rabbitmq.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName Consumer
 * @Description 消费者
 * @Author hanjun
 * @Date 2019/9/2 16:51
 **/
@Component
public class Consumer1 {

    private Logger log = LoggerFactory.getLogger(Consumer1.class);


    @RabbitListener(queuesToDeclare = @Queue(RabbitmqConfig.QUEUE_A))
    public void process(Message message, Channel channel) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.info("简单队列模式-receive-1: " + new String(message.getBody()));
    }



}
