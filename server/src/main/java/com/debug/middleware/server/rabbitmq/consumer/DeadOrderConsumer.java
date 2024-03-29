package com.debug.middleware.server.rabbitmq.consumer;

import com.debug.middleware.model.entity.UserOrder;
import com.debug.middleware.model.mapper.UserOrderMapper;
import com.debug.middleware.server.service.DeadUserOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 死信队列-真正队列消费者-用户下单支付超时消息模型
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
public class DeadOrderConsumer {
    //定义日志
    private static final Logger log = LoggerFactory.getLogger(DeadOrderConsumer.class);
    //定义Json序列化和反序列化组件
    @Autowired
    private ObjectMapper objectMapper;
    //定义用户下单操作Mapper
    @Autowired
    private UserOrderMapper userOrderMapper;
    //用户下单支付超时-处理服务实例
    @Autowired
    private DeadUserOrderService deadUserOrderService;

    /**
     * 用户下单支付超时消息模型-监听真正队列
     */
    @RabbitListener(queues = "${mq.consumer.order.real.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeMsg(@Payload Integer orderId) {
        try {
            log.info("用户下单支付超时消息模型-监听真正队列-监听到消息内容为：orderId={}", orderId);
            //查询该用户下单记录Id对应的支付状态是为"已保存"
            UserOrder userOrder = userOrderMapper.selectByIdAndStatus(orderId, 1);
            if (userOrder != null) {
                //不等于null，则代表该用户下单记录已经超时，该笔订单未支付，故而需要使该笔下单记录失效
                deadUserOrderService.updateUserOrderRecord(userOrder);
            }
        } catch (Exception e) {
            log.error("用户下单支付超时消息模型-监听真正队列-发生异常：orderId={} ", orderId, e.fillInStackTrace());
        }
    }
}