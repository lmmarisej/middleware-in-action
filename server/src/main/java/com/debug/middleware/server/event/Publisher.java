package com.debug.middleware.server.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring的事件驱动模型-生产者
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
public class Publisher {

    private static final Logger log = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * <p>sendMsg.</p>
     *
     * @param event a {@link org.springframework.context.ApplicationEvent} object.
     */
    public void sendMsg(ApplicationEvent event) {
        publisher.publishEvent(event);
        log.info("Spring事件驱动模型-发送消息：{}", event);
    }
}
