package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import com.rabbitmq.client.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消息消费端B
 *
 * @author Liuyongfei
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-06-11 13:50
 */
@Slf4j
@Data
@Component
public class Consumer2Controller {
    @RabbitListener(queues = {QueueConstants.QUEUE_NAME}, containerFactory = "customContainerFactory")
    public void work(Message message, Channel channel) throws IOException {
        // 获取消息
        String info = (String) message.getPayload();
        log.info("消费者B获取到消息: {}", info);
        try {
            // 获取header
            MessageHeaders headers = message.getHeaders();
            Long tag =  (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            TimeUnit.MILLISECONDS.sleep(1000);
            channel.basicAck(tag, false);
        } catch (InterruptedException | IOException e) {
            log.error("获取消息发生异常: " + e.getMessage());
        }
    }
}
