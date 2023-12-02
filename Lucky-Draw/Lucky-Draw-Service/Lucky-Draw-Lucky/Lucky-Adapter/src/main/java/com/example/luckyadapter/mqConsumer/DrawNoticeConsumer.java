package com.example.luckyadapter.mqConsumer;

import com.alibaba.fastjson.JSON;
import com.example.luckyclient.dto.data.DrawResultVO;
import com.example.luckydrawconfig.dto.LuckyDrawMessage;
import com.example.luckydrawconfig.util.SseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RocketMQMessageListener(topic = "draw-notice-topic", consumerGroup = "lucky_draw11")
public class DrawNoticeConsumer implements RocketMQListener<LuckyDrawMessage> {

    @Override
    public void onMessage(LuckyDrawMessage luckyDrawMessage) {
        log.info("接受到MQ消息了，{}", JSON.toJSONString(luckyDrawMessage));
        String body = luckyDrawMessage.getBody();
        DrawResultVO resultVO = JSON.parseObject(body, DrawResultVO.class);
        try {
            Map<String, SseEmitter> sseEmitterMap = SseUtil.getInstance().getSseEmitterMap();

            if (sseEmitterMap.containsKey(resultVO.getId().toString()))
                sseEmitterMap.get(resultVO.getId().toString()).send(resultVO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
