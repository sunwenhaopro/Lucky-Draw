package com.example.luckyapp.mqproducer;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckydrawconfig.dto.LuckyDrawMessage;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MailRegisterProducer {
    @Resource
    private  RocketMQTemplate rocketMQTemplate;
    public  Boolean send(UserRegisterCmd cmd) {
        final var activityDrawMessage = new LuckyDrawMessage()
                .setUuid(IdUtil.simpleUUID())
                .setBody(JSON.toJSONString(cmd));
        Message<LuckyDrawMessage> message = MessageBuilder
                .withPayload(activityDrawMessage)
                .build();
        SendResult sendResult = rocketMQTemplate.syncSend("mail-register-topic"+":tag3", message);

        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            log.info("消息发送成功，业务ID：{}.uuid:{}",
                    activityDrawMessage.getId(), activityDrawMessage.getUuid());
            return Boolean.TRUE;
        }
        log.info("消息发送失败！");
        return Boolean.FALSE;
    }
}
