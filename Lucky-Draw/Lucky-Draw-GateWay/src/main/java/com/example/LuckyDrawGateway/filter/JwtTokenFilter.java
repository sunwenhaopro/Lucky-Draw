package com.example.LuckyDrawGateway.filter;

import com.example.luckydrawconfig.util.JwtUtil;
import com.example.luckydrawconfig.vo.FailInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Order(-1)
@Data
@Configuration
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ConfigurationProperties(prefix = "ld.gateway")
public class JwtTokenFilter implements GlobalFilter {
    private String Authorization = "Authorization";
    private String ignoreRegex ="/user/login|/user/register|/message|/oauth|/activity/page|/user/verify";
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getPath().toString();
        Pattern pattern=Pattern.compile(ignoreRegex);
        Matcher matcher= pattern.matcher(url);
        if(matcher.find()) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        String token = exchange.getRequest().getHeaders().getFirst(Authorization);

        try {
            Map<String,String> userMap = JwtUtil.verifyToken(token);
            if("/v1/user/refresh".equals(url))
            {
                //不过不存在refresh字段，说明使用的access——token访问的，直接抛出异常
                // 加toString() null 无法转花而报错
                userMap.get("refreshToken").toString();
            }else {
                // refreshtoken不存在accessToken字段，防止使用refreshtoken访问非刷新业务接口
                // 直接get方法返回为null不报错，加tostring()null无法转花而报错

                userMap.get("accessToken").toString();
            }
            ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
            mutate.header("UserName", URLEncoder.encode(Objects.isNull(userMap.get("username")) ? "" : userMap.get("username").toString()), "UTF-8");
            mutate.header("Id", URLEncoder.encode(Objects.isNull(userMap.get("id")) ? "" : userMap.get("id").toString()), "UTF-8");


            return chain.filter(exchange.mutate().request(mutate.build()).build());
        } catch (Exception e) {

            return autoError(response);
        }
    }

    private Mono<Void> autoError(ServerHttpResponse resp) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(new FailInfo("token验证出错"));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));

        return resp.writeWith(Flux.just(buffer));
    }


}
