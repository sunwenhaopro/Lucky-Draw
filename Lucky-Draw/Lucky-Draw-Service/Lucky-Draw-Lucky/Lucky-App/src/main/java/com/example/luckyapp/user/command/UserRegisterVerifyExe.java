package com.example.luckyapp.user.command;

import com.example.luckyapp.assembler.UserAssembler;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydrawconfig.exception.Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class UserRegisterVerifyExe {
    private final UserGateWay userGateWay;
    private final RedisTemplate<String,Object> redisTemplate;
    public String execute(String verifyCode) {
              UserRegisterCmd cmd = (UserRegisterCmd) redisTemplate.opsForValue().getAndDelete(verifyCode);
              if(Objects.isNull(cmd))
              {
                  throw new Exception( "连接已失效！请重新注册");
              }
              userGateWay.save(UserAssembler.toAddEntity(cmd));
              return "注册成功！请前往登录界面";
    }
}
