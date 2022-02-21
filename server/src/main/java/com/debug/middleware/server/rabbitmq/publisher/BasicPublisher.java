package com.debug.middleware.server.rabbitmq.publisher;

import com.debug.middleware.server.rabbitmq.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 基本消息模型-生产者
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
public class BasicPublisher {

    private static final Logger log = LoggerFactory.getLogger(BasicPublisher.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;


    /**
     * 发送消息
     *
     * @param message a {@link java.lang.String} object.
     */
    public void sendMsg(String message) {
        if (!Strings.isNullOrEmpty(message)) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());             // 消息以JSON格式传输
                rabbitTemplate.setExchange(env.getProperty("mq.basic.info.exchange.name"));         // 消息模型中的交换机
                rabbitTemplate.setRoutingKey(env.getProperty("mq.basic.info.routing.key.name"));    // 指定消息模型中的路由

                Message msg = MessageBuilder
                        .withBody(message.getBytes(StandardCharsets.UTF_8))     // 将字符串转换为二进制数据流
                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT)        // 消息的持久化模式
                        .build();

                rabbitTemplate.convertAndSend(msg);     // 转换被发送消息

                log.info("基本消息模型-生产者-发送消息：{} ", message);
            } catch (Exception e) {
                log.error("基本消息模型-生产者-发送消息发生异常：{} ", message, e.fillInStackTrace());
            }
        }
    }

    /**
     * 发送对象类型的消息
     *
     * @param p a {@link com.debug.middleware.server.rabbitmq.entity.Person} object.
     */
    public void sendObjectMsg(Person p) {
        if (p != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.object.info.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.object.info.routing.key.name"));

                rabbitTemplate.convertAndSend(p, message -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Person.class);

                    return message;
                });

                log.info("基本消息模型-生产者-发送对象类型的消息：{} ", p);
            } catch (Exception e) {
                log.error("基本消息模型-生产者-发送对象类型的消息发生异常：{} ", p, e.fillInStackTrace());
            }
        }
    }
}

























