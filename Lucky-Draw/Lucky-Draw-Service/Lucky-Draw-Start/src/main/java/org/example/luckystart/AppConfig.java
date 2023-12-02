package org.example.luckystart;



import com.monitor.luckydrawmonitor.EnableMonitor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableMonitor
@EnableScheduling
@Configuration
@ComponentScan("com.example")
@EnableTransactionManagement
@MapperScan(basePackages = "com.example.luckyinfrastructure.mapper")
public class AppConfig {
}
