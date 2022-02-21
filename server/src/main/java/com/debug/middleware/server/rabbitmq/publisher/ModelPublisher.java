package com.debug.middleware.server.rabbitmq.publisher;

import com.debug.middleware.server.rabbitmq.entity.EventInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 消息模型-生产者
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
public class ModelPublisher {

    private static final Logger log = LoggerFactory.getLogger(ModelPublisher.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;


    /**
     * 发送消息-基于FanoutExchange消息模型
     * <p>
     * 扇形交换机会把能接收到的消息全部发送给绑定在自己身上的队列。
     * 因为广播不需要“思考”，所以扇形交换机处理消息的速度也是所有的交换机类型里面最快的。
     * <p>
     * <img src="doc-files/img.png" width="400" />
     *
     * @param info a {@link com.debug.middleware.server.rabbitmq.entity.EventInfo} object.
     */
    public void sendMsgFanout(EventInfo info) {
        if (info != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("mq.fanout.exchange.name"));

                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);

                log.info("消息模型fanoutExchange-生产者-发送消息：{} ", info);
            } catch (Exception e) {
                log.error("消息模型fanoutExchange-生产者-发送消息发生异常：{} ", info, e.fillInStackTrace());
            }
        }
    }

    /**
     * 发送消息-基于DirectExchange消息模型-one
     * <p>
     * 一个队列会和一个交换机绑定，除此之外再绑定一个routing_key，当消息被发送的时候，需要指定一个binding_key，这个消息被送达交换机的时候，
     * 就会被这个交换机送到指定的队列里面去。
     * 同样的一个binding_key也是支持应用到多个队列中的。
     * <p>
     * <img src="doc-files/img_1.png" width="400" />
     *
     * @param info a {@link com.debug.middleware.server.rabbitmq.entity.EventInfo} object.
     */
    public void sendMsgDirectOne(EventInfo info) {
        if (info != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

                rabbitTemplate.setExchange(env.getProperty("mq.direct.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.direct.routing.key.one.name"));

                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);

                log.info("消息模型DirectExchange-one-生产者-发送消息：{} ", info);
            } catch (Exception e) {
                log.error("消息模型DirectExchange-one-生产者-发送消息发生异常：{} ", info, e.fillInStackTrace());
            }
        }
    }

    /**
     * 发送消息-基于DirectExchange消息模型-two
     *
     * @param info a {@link com.debug.middleware.server.rabbitmq.entity.EventInfo} object.
     */
    public void sendMsgDirectTwo(EventInfo info) {
        if (info != null) {
            try {
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

                rabbitTemplate.setExchange(env.getProperty("mq.direct.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("mq.direct.routing.key.two.name"));

                Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(info)).build();
                rabbitTemplate.convertAndSend(msg);

                log.info("消息模型DirectExchange-two-生产者-发送消息：{} ", info);
            } catch (Exception e) {
                log.error("消息模型DirectExchange-two-生产者-发送消息发生异常：{} ", info, e.fillInStackTrace());
            }
        }
    }


    /**
     * 发送消息-基于TopicExchange消息模型
     * <p>
     * 发送到主题交换机上的消息需要携带指定规则的routing_key，主题交换机会根据这个规则将数据发送到对应的(多个)队列上。
     * <p>
     * 主题交换机的routing_key需要有一定的规则，交换机和队列的binding_key需要采用*.#.*.....的格式，每个部分用.分开，其中：
     * <li>* 表示一个单词
     * <li># 表示任意数量（零个或多个）单词。
     * <p>
     * 示例：<p>
     * <img src="doc-files/img_2.png" width="400" />
     *
     * @param msg        a {@link java.lang.String} object.
     * @param routingKey a {@link java.lang.String} object.
     */
    public void sendMsgTopic(String msg, String routingKey) {
        //判断对象是否为null
        if (!Strings.isNullOrEmpty(msg) && !Strings.isNullOrEmpty(routingKey)) {
            try {
                //设置消息的传输格式为json
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                //指定交换机
                rabbitTemplate.setExchange(env.getProperty("mq.topic.exchange.name"));
                //指定路由的实际取值，根据不同取值，RabbitMQ将自行进行匹配通配符，从而路由到不同的队列中
                rabbitTemplate.setRoutingKey(routingKey);

                //创建消息
                Message message = MessageBuilder.withBody(msg.getBytes(StandardCharsets.UTF_8)).build();
                //发送消息
                rabbitTemplate.convertAndSend(message);

                //打印日志
                log.info("消息模型TopicExchange-生产者-发送消息：{}  路由：{} ", msg, routingKey);
            } catch (Exception e) {
                log.error("消息模型TopicExchange-生产者-发送消息发生异常：{} ", msg, e.fillInStackTrace());
            }
        }
    }


}



















