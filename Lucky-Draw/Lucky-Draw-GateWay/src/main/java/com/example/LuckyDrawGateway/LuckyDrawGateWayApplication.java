package com.example.LuckyDrawGateway;

import com.example.LuckyDrawGateway.resolver.IpKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class LuckyDrawGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyDrawGateWayApplication.class, args);
    }
    @Bean("ipKeyResolver")
    public IpKeyResolver ipKeyResolver(){
        return new IpKeyResolver();
    }
}
