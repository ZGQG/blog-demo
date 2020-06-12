package com.fullstackboy.rabbitmqdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 * 设置prefetchCount为1，消费者在接收到队列里的消息但没有返回确认结果之前,队列不会将新的消息分发给该消费者
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-06-12 06:10
 */
@Slf4j
@Configuration
public class RabbitConsumerConfig {
    @Bean("customContainerFactory")
    public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // 设置为消息ack手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置当前信道最大预获取消息量为1
        factory.setPrefetchCount(1);
        configurer.configure(factory,connectionFactory);
        return  factory;
    }
}
