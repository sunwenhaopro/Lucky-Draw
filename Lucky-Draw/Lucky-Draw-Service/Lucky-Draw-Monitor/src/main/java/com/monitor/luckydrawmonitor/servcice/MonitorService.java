package com.monitor.luckydrawmonitor.servcice;


import com.monitor.luckydrawmonitor.entity.*;
import com.monitor.luckydrawmonitor.util.BrowserTypeUtil;
import com.monitor.luckydrawmonitor.util.MediaTypeUtil;
import com.monitor.luckydrawmonitor.util.PositionUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;


@Service
public class MonitorService {
    @Resource
    RedisService redisService;

    @Resource
    SseService sseService;
     private final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");

    public void startMonitor(HttpServletRequest request) {
     Record.pv.increment();
     String ip= request.getRemoteAddr();
     String path = request.getRequestURI();
     if(path.equals("/register/verifyCode"))
     {
         Record.newUserNumber.incrementAndGet();
     }
     String browser = BrowserTypeUtil.getBrowser(request);
     String os = MediaTypeUtil.getOs(request);
     Record.browser.get(browser).increment();
     Record.mediaTypeNumber.get(os).increment();
     Position position = PositionUtil.getPositionInfo(ip);
     redisService.recordPositionInfo(position);
     redisService.recordInterfaceInfo(path);
    }

    public void endMonitor(ServerHttpRequest request ,Object body) throws JsonProcessingException {

        if(request.getURI().getPath().equals("/v1/activity/draw"))
        {
            if("DrawResultVO".equals(body.getClass().getSimpleName()))
            {
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString (body);
                DrawResult drawResult = mapper.readValue(json, DrawResult.class);
                if(drawResult.getIsDraw())
                {
                    sseService.sendDrawResult(drawResult);
                }
            }
        }
        if("FailInfo".equals(body.getClass().getSimpleName())){
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString (body);
                MonitorFail failInfo = mapper.readValue(json, MonitorFail.class);
                Record.status.get("exception").increment();
                Record.fail.increment();
                ExceptionResult exceptionResult=new ExceptionResult();
                exceptionResult.setPath(request.getURI().getPath());
                exceptionResult.setDescription(failInfo.getExpection());
                exceptionResult.setTime(formatter.format(System.currentTimeMillis()));
                sseService.sendExceptionInfo(exceptionResult);
                return ;
        }
        Record.success.increment();
    }
}
