package com.monitor.luckydrawmonitor.config;


import com.monitor.luckydrawmonitor.servcice.MonitorService;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.Objects;

@Order(1)
@ControllerAdvice
public class ResponseBodyAdviceImpl implements ResponseBodyAdvice<Object> {

    @Resource
    MonitorService monitorService;
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        try {
            if(Objects.nonNull(body))
             monitorService.endMonitor(request, body);
        }catch (Exception e){
            e.printStackTrace();
        }
        return body;
    }
}