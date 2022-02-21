package com.debug.middleware.server.config;/**
 * Created by Administrator on 2019/5/4.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * <p>SchedulerConfig class.</p>
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/4 19:15
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Configuration
public class SchedulerConfig {

    /**
     * <p>scheduledExecutorService.</p>
     *
     * @return a {@link org.springframework.scheduling.TaskScheduler} object.
     */
    @Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }

}

















