package com.example.luckyadapter.mqConsumer;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckydrawconfig.dto.LuckyDrawMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RocketMQMessageListener(topic = "mail-register-topic",selectorExpression = "tag3",selectorType = SelectorType.TAG, consumerGroup = "lucky_draw12")
public class MailRegisterConsumer implements RocketMQListener<LuckyDrawMessage> {
    @Resource
    private  RedisTemplate<String , Object> redisTemplate;
    @Resource
    private  JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private  String sender;
    @Value("${register.interface}")
    private String address;
    @Override
    public void onMessage(LuckyDrawMessage luckyDrawMessage) {
        System.out.println(sender);
        log.info("接受到MQ消息了，{}", JSON.toJSONString(luckyDrawMessage));
        String body = luckyDrawMessage.getBody();
        UserRegisterCmd cmd = JSON.parseObject(body, UserRegisterCmd.class);
        String token= IdUtil.simpleUUID();
        try {
            sendMail(cmd,token);
            // 加上时间单位，不然报错
            //redisTemplate取值报错org.springframework.data.redis.serializer.SerializationException:
            // Could not read JSON: Invalid UTF-32 character 0x22776961 (above 0x0010ffff) at char #15,
            // byte #63); nested exception is java.io.CharConversionException: Invalid UTF-32 character 0x22776961
            // (above 0x0010ffff) at char #15, byte #63)
            redisTemplate.opsForValue().set(token,cmd,60L, TimeUnit.SECONDS);
        } catch (Exception ex) {
            log.info(cmd.getMail()+"发送失败");
        }
    }
   public void sendMail(UserRegisterCmd cmd,String token) throws Exception
   {
       MimeMessage mailMessage = javaMailSender.createMimeMessage();
       MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
           messageHelper.setFrom(sender);
           messageHelper.setTo(cmd.getMail());
           messageHelper.setSubject("注册邮件");
           String html = "<html>\n" +
                   "<body>\n" +
                   "<p>请点击下方链接验证</p>\n" +
                   "<a href=\"" + address + token + "\"> "+ address + token + "</a>" +
                   "</body>\n" +
                   "</html>";
           messageHelper.setText(html, true);
           javaMailSender.send(mailMessage);
   }
}
