package com.debug.middleware.server.rabbitmq.consumer;

import com.debug.middleware.server.rabbitmq.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 基本消息模型-消费者
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
public class BasicConsumer {

    private static final Logger log = LoggerFactory.getLogger(BasicConsumer.class);

    @Autowired
    public ObjectMapper objectMapper;

    /**
     * 监听并消费队列中的消息-在这里采用单一容器工厂实例即可
     *
     * @param msg an array of byte.
     */
    @RabbitListener(queues = "${mq.basic.info.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload byte[] msg) {
        try {
            String message = new String(msg, StandardCharsets.UTF_8);
            log.info("基本消息模型-消费者-监听消费到消息：{} ", message);
        } catch (Exception e) {
            log.error("基本消息模型-消费者-发生异常：", e.fillInStackTrace());
        }
    }


    /**
     * 监听并消费队列中的消息-监听消费处理对象信息-在这里采用单一容器工厂实例即可
     *
     * @param person a {@link com.debug.middleware.server.rabbitmq.entity.Person} object.
     */
    @RabbitListener(queues = "${mq.object.info.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeObjectMsg(@Payload Person person) {
        try {
            log.info("基本消息模型-监听消费处理对象信息-消费者-监听消费到消息：{} ", person);
        } catch (Exception e) {
            log.error("基本消息模型-监听消费处理对象信息-消费者-发生异常：", e.fillInStackTrace());
        }
    }
}






























