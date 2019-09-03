package com.example.rabbitmq.directexchange;

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
 * @ClassName DirectConsumerA
 * @Description fanout消费者
 * @Author hanjun
 * @Date 2019/9/3 11:48
 **/
@Component
@Slf4j
public class DirectConsumerB {

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
            value = @Queue(value = RabbitmqConfig.QUEUE_DIRECT_A, durable = "true"),
            exchange = @Exchange(name = RabbitmqConfig.EXCHANGE_DIRECT, durable = "true", type = "direct"),key = RabbitmqConfig.EXCHANGE_EDIRECT_KEY))
    public void process(Message message, Channel channel) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.info("direct-exchange模式-receive-B: " + new String(message.getBody()));
    }
}
