package com.monitor.luckydrawmonitor.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class MediaTypeUtil {
    public static String getOs(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.contains("Windows")||userAgent.contains("Macintosh")||userAgent.startsWith("curl")) {
            return "PC";
        } else{ return "Mobile";}
    }
}
