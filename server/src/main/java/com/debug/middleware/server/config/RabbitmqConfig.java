package com.debug.middleware.server.config;

import com.debug.middleware.server.rabbitmq.consumer.KnowledgeManualConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ自定义注入Bean配置
 * Created by steadyjack on 2019/3/30.
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Configuration
public class RabbitmqConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;     // 连接工厂实例

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;       // 消息监听器所在的容器工厂配置类实例

    /**
     * 单一消费者
     *
     * @return a {@link org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory} object.
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        // 定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        return factory;
    }

    /**
     * 多个消费者
     *
     * @return a {@link org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory} object.
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // NONE：无需确认，消费者监听到消息无需确认，不会发送任何反馈信息给RabbitMQ服务器
        //      因为rabbitmq server认为推送的消息已被成功消费，所以推送出去的消息不会暂存在server端。
        // AUTO：自动确认，消费者监听到消息，自动发送一个ACK反馈信息给RabbitMQ服务器
        //      由spring-rabbit依据消息处理逻辑是否抛出异常自动发送ack（无异常）或nack（异常）到server端。
        // MANUAL：手动确认，消费者监听到消息，需要开发者代码手动发送一个ACK反馈信息给RabbitMQ服务器
        //      需要人为地获取到channel之后调用方法向server发送ack（或消费失败时的nack）信息。
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(10);     // 并发消费者初始数量
        factory.setMaxConcurrentConsumers(15);
        // 在 ACK 非 NONE 的模式下，没有收到 ack 的消息数量达到 N 就不会再向 channel
        factory.setPrefetchCount(10);       // 每个消费者预拉取消息的数量
        return factory;
    }

    /**
     * RabbitMQ发送消息的操作组件实例
     *
     * @return a {@link org.springframework.amqp.rabbit.core.RabbitTemplate} object.
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);       // 发送消息后进行确认
        connectionFactory.setPublisherReturns(true);        // 发送消息后返回确认信息
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        // 发送成功
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        // 发送完成反馈
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    //定义读取配置文件的环境变量实例
    @Autowired
    private Environment env;

    /**
     * 创建简单消息模型：队列、交换机和路由
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建队列
    @Bean(name = "basicQueue")
    public Queue basicQueue() {
        return new Queue(env.getProperty("mq.basic.info.queue.name"), true);
    }

    //创建交换机：在这里以DirectExchange为例，在后面章节中我们将继续详细介绍这种消息模型
    /**
     * <p>basicExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.DirectExchange} object.
     */
    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange(env.getProperty("mq.basic.info.exchange.name"), true, false);
    }

    //创建绑定
    /**
     * <p>basicBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("mq.basic.info.routing.key.name"));
    }


    /**
     * 创建简单消息模型-对象类型：队列、交换机和路由
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建队列
    @Bean(name = "objectQueue")
    public Queue objectQueue() {
        return new Queue(env.getProperty("mq.object.info.queue.name"), true);
    }

    //创建交换机：在这里以DirectExchange为例，在后面章节中我们将继续详细介绍这种消息模型
    /**
     * <p>objectExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.DirectExchange} object.
     */
    @Bean
    public DirectExchange objectExchange() {
        return new DirectExchange(env.getProperty("mq.object.info.exchange.name"), true, false);
    }

    //创建绑定
    /**
     * <p>objectBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding objectBinding() {
        return BindingBuilder.bind(objectQueue()).to(objectExchange()).with(env.getProperty("mq.object.info.routing.key.name"));
    }


    /**
     * 创建消息模型-fanoutExchange
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建队列1
    @Bean(name = "fanoutQueueOne")
    public Queue fanoutQueueOne() {
        return new Queue(env.getProperty("mq.fanout.queue.one.name"), true);
    }

    //创建队列2
    /**
     * <p>fanoutQueueTwo.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean(name = "fanoutQueueTwo")
    public Queue fanoutQueueTwo() {
        return new Queue(env.getProperty("mq.fanout.queue.two.name"), true);
    }

    //创建交换机-fanoutExchange
    /**
     * <p>fanoutExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.FanoutExchange} object.
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(env.getProperty("mq.fanout.exchange.name"), true, false);
    }

    //创建绑定1
    /**
     * <p>fanoutBindingOne.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding fanoutBindingOne() {
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    //创建绑定2
    /**
     * <p>fanoutBindingTwo.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding fanoutBindingTwo() {
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }


    /**
     * 创建消息模型-directExchange
     *
     * @return a {@link org.springframework.amqp.core.DirectExchange} object.
     */

    //创建交换机-directExchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(env.getProperty("mq.direct.exchange.name"), true, false);
    }

    //创建队列1
    /**
     * <p>directQueueOne.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean(name = "directQueueOne")
    public Queue directQueueOne() {
        return new Queue(env.getProperty("mq.direct.queue.one.name"), true);
    }

    //创建队列2
    /**
     * <p>directQueueTwo.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean(name = "directQueueTwo")
    public Queue directQueueTwo() {
        return new Queue(env.getProperty("mq.direct.queue.two.name"), true);
    }

    //创建绑定1
    /**
     * <p>directBindingOne.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding directBindingOne() {
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(env.getProperty("mq.direct.routing.key.one.name"));
    }

    //创建绑定2
    /**
     * <p>directBindingTwo.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding directBindingTwo() {
        return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with(env.getProperty("mq.direct.routing.key.two.name"));
    }


    /**
     * 创建消息模型-topicExchange
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */

    //创建交换机-topicExchange
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(env.getProperty("mq.topic.exchange.name"), true, false);
    }

    //创建队列1
    /**
     * <p>topicQueueOne.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean(name = "topicQueueOne")
    public Queue topicQueueOne() {
        return new Queue(env.getProperty("mq.topic.queue.one.name"), true);
    }

    //创建队列2
    /**
     * <p>topicQueueTwo.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean(name = "topicQueueTwo")
    public Queue topicQueueTwo() {
        return new Queue(env.getProperty("mq.topic.queue.two.name"), true);
    }

    //创建绑定1
    /**
     * <p>topicBindingOne.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding topicBindingOne() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(env.getProperty("mq.topic.routing.key.one.name"));
    }

    //创建绑定2
    /**
     * <p>topicBindingTwo.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding topicBindingTwo() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(env.getProperty("mq.topic.routing.key.two.name"));
    }


    /* 创建自动确认消费消息模型 **/

    /**
     * 单一消费者-确认模式为AUTO
     *
     * @return a {@link org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory} object.
     */
    @Bean(name = "singleListenerContainerAuto")
    // singleListenerContainerAuto在消费者端指定@RabbitListener(queues = "${mq.auto.knowledge.queue.name}",
    // containerFactory = "singleListenerContainerAuto")
    public SimpleRabbitListenerContainerFactory listenerContainerAuto() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        //设置确认消费模式为自动确认消费-AUTO
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    //创建队列
    /**
     * <p>autoQueue.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean(name = "autoQueue")
    public Queue autoQueue() {
        return new Queue(env.getProperty("mq.auto.knowledge.queue.name"), true);
    }

    //创建交换机
    /**
     * <p>autoExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.DirectExchange} object.
     */
    @Bean
    public DirectExchange autoExchange() {
        return new DirectExchange(env.getProperty("mq.auto.knowledge.exchange.name"), true, false);
    }

    //创建绑定
    /**
     * <p>autoBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding autoBinding() {
        return BindingBuilder.bind(autoQueue()).to(autoExchange()).with(env.getProperty("mq.auto.knowledge.routing.key.name"));
    }


    /**
     * 单一消费者-确认模式为MANUAL
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建队列
    @Bean(name = "manualQueue")
    public Queue manualQueue() {
        return new Queue(env.getProperty("mq.manual.knowledge.queue.name"), true);
    }

    //创建交换机
    /**
     * <p>manualExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange manualExchange() {
        return new TopicExchange(env.getProperty("mq.manual.knowledge.exchange.name"), true, false);
    }

    //创建绑定
    /**
     * <p>manualBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding manualBinding() {
        return BindingBuilder.bind(manualQueue()).to(manualExchange()).with(env.getProperty("mq.manual.knowledge.routing.key.name"));
    }

    //定义手动确认消费模式对应的消费者实例
    @Autowired
    private KnowledgeManualConsumer knowledgeManualConsumer;

    /**
     * 创建单一消费者-确认模式为MANUAL-并指定监听的队列和消费者
     *
     * @param manualQueue a {@link org.springframework.amqp.core.Queue} object.
     * @return a {@link org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer} object.
     */
    @Bean(name = "simpleContainerManual")
    public SimpleMessageListenerContainer simpleContainer(@Qualifier("manualQueue") Queue manualQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        // 并发配置
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setPrefetchCount(1);

        // 消息确认模式-采用人为手动确认消费机制
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(manualQueue);
        container.setMessageListener(knowledgeManualConsumer);

        return container;
    }


    /**
     * 用户登录成功写日志消息模型创建
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建队列
    @Bean(name = "loginQueue")
    public Queue loginQueue() {
        return new Queue(env.getProperty("mq.login.queue.name"), true);
    }

    //创建交换机
    /**
     * <p>loginExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange loginExchange() {
        return new TopicExchange(env.getProperty("mq.login.exchange.name"), true, false);
    }

    //创建绑定
    /**
     * <p>loginBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding loginBinding() {
        return BindingBuilder.bind(loginQueue()).to(loginExchange()).with(env.getProperty("mq.login.routing.key.name"));
    }


    /**
     * 死信队列消息模型构建
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建死信队列
    @Bean
    public Queue basicDeadQueue() {
        Map<String, Object> args = new HashMap();
        // 重新设置消息的投递路由和投递交换机
        args.put("x-dead-letter-exchange", env.getProperty("mq.dead.exchange.name"));
        args.put("x-dead-letter-routing-key", env.getProperty("mq.dead.routing.key.name"));

        //设定TTL，单位为ms，在这里指的是10s
        args.put("x-message-ttl", 10000);  // 消息超时后，由MQ将其投递到上面指定的交换机，交换机通过上面指定的路由key再投递到指定的队列，消费者拉取队列中消息进行消费
        return new Queue(env.getProperty("mq.dead.queue.name"), true, false, false, args);
    }

    //创建“基本消息模型”的基本交换机 - 面向生产者
    /**
     * <p>basicProducerExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange basicProducerExchange() {
        return new TopicExchange(env.getProperty("mq.producer.basic.exchange.name"), true, false);
    }

    //创建“基本消息模型”的基本绑定-基本交换机+基本路由 - 面向生产者
    /**
     * <p>basicProducerBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding basicProducerBinding() {
        // 如果交换机接收到的路由key是mq.producer.basic.routing.key.name，就将消息放入死信队列
        return BindingBuilder.bind(basicDeadQueue()).to(basicProducerExchange()).with(env.getProperty("mq.producer.basic.routing.key.name"));
    }

    //创建真正队列 - 面向消费者
    /**
     * <p>realConsumerQueue.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean
    public Queue realConsumerQueue() {
        return new Queue(env.getProperty("mq.consumer.real.queue.name"), true);
    }

    //创建死信交换机
    /**
     * <p>basicDeadExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange basicDeadExchange() {
        return new TopicExchange(env.getProperty("mq.dead.exchange.name"), true, false);
    }

    //创建死信路由及其绑定
    /**
     * <p>basicDeadBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding basicDeadBinding() {
        // 如果交换机接收到的消息的路由key为mq.dead.routing.key.name，就投递到消费者的队列
        return BindingBuilder.bind(realConsumerQueue()).to(basicDeadExchange()).with(env.getProperty("mq.dead.routing.key.name"));
    }


    /**
     * 用户下单支付超时-RabbitMQ死信队列消息模型构建
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建死信队列
    @Bean
    public Queue orderDeadQueue() {
        Map<String, Object> args = new HashMap();
        args.put("x-dead-letter-exchange", env.getProperty("mq.order.dead.exchange.name"));
        args.put("x-dead-letter-routing-key", env.getProperty("mq.order.dead.routing.key.name"));

        //设定TTL，单位为ms，在这里为了测试方便，设置为10s，当然实际业务场景可能为1h或者更长
        args.put("x-message-ttl", 10000);
        return new Queue(env.getProperty("mq.order.dead.queue.name"), true, false, false, args);
    }

    //创建“基本消息模型”的基本交换机 - 面向生产者
    /**
     * <p>orderProducerExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange orderProducerExchange() {
        return new TopicExchange(env.getProperty("mq.producer.order.exchange.name"), true, false);
    }

    //创建“基本消息模型”的基本绑定-基本交换机+基本路由 - 面向生产者
    /**
     * <p>orderProducerBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding orderProducerBinding() {
        return BindingBuilder.bind(orderDeadQueue()).to(orderProducerExchange()).with(env.getProperty("mq.producer.order.routing.key.name"));
    }

    //创建真正队列 - 面向消费者
    /**
     * <p>realOrderConsumerQueue.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean
    public Queue realOrderConsumerQueue() {
        return new Queue(env.getProperty("mq.consumer.order.real.queue.name"), true);
    }

    //创建死信交换机
    /**
     * <p>basicOrderDeadExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange basicOrderDeadExchange() {
        return new TopicExchange(env.getProperty("mq.order.dead.exchange.name"), true, false);
    }

    //创建死信路由及其绑定
    /**
     * <p>basicOrderDeadBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding basicOrderDeadBinding() {
        return BindingBuilder.bind(realOrderConsumerQueue()).to(basicOrderDeadExchange()).with(env.getProperty("mq.order.dead.routing.key.name"));
    }


    /**
     * Redisson篇章-RabbitMQ死信队列的缺陷
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */

    //创建死信队列-由死信交换机+死信路由组成
    @Bean
    public Queue redissonBasicDeadQueue() {
        Map<String, Object> argsMap = new HashMap<>();
        argsMap.put("x-dead-letter-exchange", env.getProperty("mq.redisson.dead.exchange.name"));
        argsMap.put("x-dead-letter-routing-key", env.getProperty("mq.redisson.dead.routing.key.name"));
        return new Queue(env.getProperty("mq.redisson.dead.queue.name"), true, false, false, argsMap);
    }

    //创建基本交换机
    /**
     * <p>redissonBasicExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange redissonBasicExchange() {
        return new TopicExchange(env.getProperty("mq.redisson.dead.basic.exchange.name"), true, false);
    }

    //创建基本路由及其绑定-绑定到死信队列
    /**
     * <p>redissonBasicBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding redissonBasicBinding() {
        return BindingBuilder.bind(redissonBasicDeadQueue())
                .to(redissonBasicExchange()).with(env.getProperty("mq.redisson.dead.basic.routing.key.name"));
    }

    //创建死信交换机
    /**
     * <p>redissonBasicDeadExchange.</p>
     *
     * @return a {@link org.springframework.amqp.core.TopicExchange} object.
     */
    @Bean
    public TopicExchange redissonBasicDeadExchange() {
        return new TopicExchange(env.getProperty("mq.redisson.dead.exchange.name"), true, false);
    }

    //创建真正队列 - 面向消费者
    /**
     * <p>redissonBasicDeadRealQueue.</p>
     *
     * @return a {@link org.springframework.amqp.core.Queue} object.
     */
    @Bean
    public Queue redissonBasicDeadRealQueue() {
        return new Queue(env.getProperty("mq.redisson.real.queue.name"), true);
    }

    //创建死信路由及其绑定-绑定到真正的队列
    /**
     * <p>redissonBasicDeadRealBinding.</p>
     *
     * @return a {@link org.springframework.amqp.core.Binding} object.
     */
    @Bean
    public Binding redissonBasicDeadRealBinding() {
        return BindingBuilder.bind(redissonBasicDeadRealQueue())
                .to(redissonBasicDeadExchange()).with(env.getProperty("mq.redisson.dead.routing.key.name"));
    }
}


















