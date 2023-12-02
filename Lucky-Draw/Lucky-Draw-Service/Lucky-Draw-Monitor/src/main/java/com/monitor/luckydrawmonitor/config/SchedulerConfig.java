package com.monitor.luckydrawmonitor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    @Value("${monitor.scheduler.pool-size}")
    private Integer POOL_SIZE;
    @Override
    public void configureTasks(ScheduledTaskRegistrar register) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("monitor-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();
        register.setTaskScheduler(threadPoolTaskScheduler);
    }
}
