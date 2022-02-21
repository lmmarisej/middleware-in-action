package com.debug.middleware.server.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * <p>Consumer class.</p>
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Component
@EnableAsync
public class Consumer implements ApplicationListener<LoginEvent> {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * {@inheritDoc}
     *
     * 监听消费消息-事件信息
     */
    @Override
    @Async
    public void onApplicationEvent(LoginEvent loginEvent) {
        log.info("Spring事件驱动模型-接收消息：{}", loginEvent);
        loginEvent.hasBeenConsumed();
    }
}













