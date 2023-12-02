package com.monitor.luckydrawmonitor.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class BrowserTypeUtil {

    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        if(userAgent.contains("edg")) {
            return "Edge";
        }
        if(userAgent.contains("chrome")&&userAgent.contains("safari")) {
            //chrome浏览器会发送Chrome/119.0.0.0 Safari/537.36
            return "Chrome";
        }
        if(userAgent.contains("safari")) {
            //safari只会发送Safari
            return "Safari";
        }
        if(userAgent.contains("firefox")) {
            return "Firefox";
        }
        log.warn("未获取到浏览器类型:{}",userAgent);
        return "Others";
    }
}
