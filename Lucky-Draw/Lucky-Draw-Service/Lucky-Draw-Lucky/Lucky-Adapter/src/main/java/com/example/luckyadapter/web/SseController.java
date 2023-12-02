package com.example.luckyadapter.web;


import com.example.luckydrawconfig.util.SseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SseController {

    SseUtil sseUtil=SseUtil.getInstance();

    @GetMapping("/message/{id}")
    @ResponseBody
    public SseEmitter subscribe(@PathVariable String id) {
            SseEmitter sseEmitter = new SseEmitter(30 * 60 * 1000L);
            sseUtil.getSseEmitterMap().put(id, sseEmitter);
            sseEmitter.onCompletion(() -> sseUtil.getSseEmitterMap().remove(id));
            sseEmitter.onError((e) -> sseUtil.getSseEmitterMap().remove(id));
            return sseEmitter;
    }
}
