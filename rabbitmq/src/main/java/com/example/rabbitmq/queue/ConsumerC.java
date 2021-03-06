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
public class ConsumerC {

    private Logger log = LoggerFactory.getLogger(ConsumerC.class);

    /*@RabbitListener(queuesToDeclare = @Queue(RabbitmqConfig.QUEUE_DIRECT_A))*/
    public void process(Message message, Channel channel) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.info("简单队列模式-receive-0: " + new String(message.getBody()));
    }



}
