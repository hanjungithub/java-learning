package com.example.rabbitmq.topicexchange;

import com.example.rabbitmq.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName TopicConsumerA
 * @Description Topic消费者
 * @Author hanjun
 * @Date 2019/9/3 11:48
 **/
@Component
@Slf4j
public class TopicConsumerA {

    /**
     * @QueueBinding 将交换机，队列，按指定的routingkey绑定起来
     *   @Queue value队列名称，durable 是否持久化
     *   @Exchange name交换机名称,durable 是否持久化，type交换机类型
     *   key routingkey  路由规则
     *   这个注解呢很强大，它可以直接帮我们创建交换机，队列，routingkey，然后把交换机和队列按指定的routingkey绑定起来。
     * @param message
     * @param channel
     * @throws IOException
     */

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqConfig.QUEUE_TOPIC_A, durable = "true"),
            exchange = @Exchange(name = RabbitmqConfig.EXCHANGE_TOPIC, durable = "true", type = "topic"),key = "EXCHANGE_TOPIC.MESSAGE1"))
    public void process(Message message, Channel channel) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("topic-exchange模式-receive-A: " + new String(message.getBody()));
    }
}
