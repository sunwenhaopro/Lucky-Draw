package com.monitor.luckydrawmonitor.config;

import com.monitor.luckydrawmonitor.intercepter.MonitorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MonitorWebMvcConfig implements WebMvcConfigurer {
       @Resource
       MonitorInterceptor monitorInterceptor;
       @Override
       public void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(monitorInterceptor)
                   .addPathPatterns("/**")
                   .excludePathPatterns("/monitor/sse/**");
       }
}
