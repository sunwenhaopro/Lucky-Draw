package com.example.luckyadapter.mqConsumer;


import com.alibaba.fastjson.JSON;
import com.example.luckyapp.record.command.RecordAddCmdExe;
import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckydrawconfig.dto.LuckyDrawMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;

import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
@RocketMQMessageListener(topic = "activity-draw-topic",consumerGroup = "lucky_draw10")
public class ActivityDrawMessageConsumer implements RocketMQListener<LuckyDrawMessage> {

    private final RecordAddCmdExe recordAddCmdExe;

    @Override
    public void onMessage(LuckyDrawMessage luckyDrawMessage) {
        log.info("接受到MQ消息了，{}", JSON.toJSONString(luckyDrawMessage));
        String body =luckyDrawMessage.getBody();
        RecordAddCmd cmd = JSON.parseObject(body, RecordAddCmd.class);
        recordAddCmdExe.execute(cmd);
    }
}
