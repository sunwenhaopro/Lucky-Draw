package com.monitor.luckydrawmonitor.controller;

import com.monitor.luckydrawmonitor.entity.Record;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;



@RestController
public class SSEController {
    @GetMapping("/monitor/sse/{id}")
    @CrossOrigin
    public SseEmitter subscribe(@PathVariable Integer id) {
        SseEmitter sseEmitter = new SseEmitter( 30 * 60 * 1000L);
        Record.sseEmitterMap.put(id, sseEmitter);
        sseEmitter.onCompletion(() ->  { Record.sseEmitterMap.remove(id);Record.hasSseLink=Boolean.FALSE;});
        sseEmitter.onError((e) -> { Record.sseEmitterMap.remove(id);Record.hasSseLink=Boolean.FALSE;});
        Record.hasSseLink=true;
        return sseEmitter;
    }
}
