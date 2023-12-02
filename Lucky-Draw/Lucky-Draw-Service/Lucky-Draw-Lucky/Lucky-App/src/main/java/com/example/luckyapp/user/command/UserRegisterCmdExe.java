package com.example.luckyapp.user.command;

import com.example.luckyapp.mqproducer.MailRegisterProducer;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.UserEntity;
import com.example.luckydrawconfig.exception.Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class UserRegisterCmdExe {
    private final UserGateWay userGateWay;
    private final MailRegisterProducer mailSendProduce;
    public Boolean execute(UserRegisterCmd cmd){
     UserEntity  loadUserEntity1=  userGateWay.findByUserName(null,cmd.getUsername());
     if(Objects.nonNull(loadUserEntity1)){
         throw new Exception("账号已存在");
     }
        UserEntity  loadUserEntity2= userGateWay.findByMail(cmd.getMail());
        if(Objects.nonNull(loadUserEntity2)){
            throw new Exception("邮箱已被注册");
        }
        try {
            return mailSendProduce.send(cmd);
        }catch (java.lang.Exception e)
        {
            throw new Exception("服务繁忙");
        }
    }

}
