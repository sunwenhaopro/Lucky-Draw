package com.example.luckydrawconfig.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Data
@Getter
public class SseUtil {
    private static volatile SseUtil instance= null;
    private Map<String , SseEmitter> sseEmitterMap;
    private SseUtil()
    {
       sseEmitterMap=new ConcurrentHashMap<>();
    }
      public  static  SseUtil getInstance()
     {
         if(instance==null)
         {
             synchronized (SseUtil.class)
             {
                 if(instance == null )
                 {
                     instance= new SseUtil();
                 }
             }
         }
         return instance;
     }
}
