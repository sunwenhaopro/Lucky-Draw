package com.example.LuckyDrawCommon.interceptor;

import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> userMap = new HashMap<>();
        String username = URLDecoder.decode(Objects.isNull(request.getHeader("UserName")) ? "" : request.getHeader("UserName"), "UTF-8");
        String id = URLDecoder.decode(Objects.isNull(request.getHeader("Id")) ? "" : request.getHeader("Id"), "UTF-8");
        userMap.put("UserName", username);
        userMap.put("Id", id);
        SecurityUtil.setMap(userMap);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        SecurityUtil.remove();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
