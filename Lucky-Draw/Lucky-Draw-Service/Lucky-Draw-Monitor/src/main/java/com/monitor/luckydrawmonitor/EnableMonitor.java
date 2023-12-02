package com.monitor.luckydrawmonitor;

import com.monitor.luckydrawmonitor.config.MonitorConfig;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.lang.annotation.*;

@EnableScheduling
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MonitorConfig.class)
public @interface EnableMonitor {
}
