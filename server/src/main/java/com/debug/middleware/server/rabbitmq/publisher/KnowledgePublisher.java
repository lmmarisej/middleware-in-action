package com.debug.middleware.server.rabbitmq.publisher;

import com.debug.middleware.server.rabbitmq.entity.KnowledgeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 确认消费模式-生产者
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
public class KnowledgePublisher {

    private static final Logger log = LoggerFactory.getLogger(KnowledgePublisher.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 基于AUTO机制-生产者发送消息
     *
     * @param info a {@link com.debug.middleware.server.rabbitmq.entity.KnowledgeInfo} object.
     */
    public void sendAutoMsg(KnowledgeInfo info) {
        try {
            if (info != null) {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.auto.knowledge.exchange.name"));     // 指定自动确认交换机
                rabbitTemplate.setRoutingKey(env.getProperty("mq.auto.knowledge.routing.key.name"));

                Message message = MessageBuilder
                        .withBody(objectMapper.writeValueAsBytes(info))
                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                        .build();
                rabbitTemplate.convertAndSend(message);
                log.info("基于AUTO机制-生产者发送消息-内容为：{} ", info);
            }
        } catch (Exception e) {
            log.error("基于AUTO机制-生产者发送消息-发生异常：{} ", info, e.fillInStackTrace());
        }
    }

}
































