package com.example.luckyadapter.mqConsumer;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.example.luckyclient.dto.query.RecordWarnQuery;
import com.example.luckydrawconfig.dto.LuckyDrawMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
@RocketMQMessageListener(topic = "mail-warn-topic",selectorExpression = "tag4" ,selectorType = SelectorType.TAG,consumerGroup = "lucky_draw13")
public class MailWarnDDLConsumer  implements RocketMQListener<LuckyDrawMessage> {
    @Resource
    private RedisTemplate<String , Object> redisTemplate;
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private  String sender;
    @Value("${register.interface}")
    private String address;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public void onMessage(LuckyDrawMessage luckyDrawMessage) {
        System.out.println(sender);
        log.info("接受到MQ消息了，{}", JSON.toJSONString(luckyDrawMessage));
        String body = luckyDrawMessage.getBody();
        RecordWarnQuery recordWarnQuery = JSON.parseObject(body, RecordWarnQuery.class);
        String token= IdUtil.simpleUUID();
        try {
            sendMail(recordWarnQuery);
            // 加上时间单位，不然报错
            //redisTemplate取值报错org.springframework.data.redis.serializer.SerializationException:
            // Could not read JSON: Invalid UTF-32 character 0x22776961 (above 0x0010ffff) at char #15,
            // byte #63); nested exception is java.io.CharConversionException: Invalid UTF-32 character 0x22776961
            // (above 0x0010ffff) at char #15, byte #63)
        } catch (Exception ex) {
            log.info(recordWarnQuery.getMail()+"发送失败");
        }
    }
    public void sendMail(RecordWarnQuery recordWarnQuery) throws Exception
    {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
        messageHelper.setFrom(sender);
        messageHelper.setTo(recordWarnQuery.getMail());
        messageHelper.setSubject("warn邮件");
        Context context=new Context();
        context.setVariable("prizeName",recordWarnQuery.getPrizeName());
        context.setVariable("activityName",recordWarnQuery.getActivityName());
        context.setVariable("awardName",recordWarnQuery.getAwardName());
        context.setVariable("userName",recordWarnQuery.getUsername());
        String text=templateEngine.process("RecordWarnMail",context);
        messageHelper.setText(text, true);
        javaMailSender.send(mailMessage);
    }
}

