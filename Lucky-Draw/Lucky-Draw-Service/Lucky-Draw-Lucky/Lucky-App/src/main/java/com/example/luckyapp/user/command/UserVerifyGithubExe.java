package com.example.luckyapp.user.command;

import com.example.luckyapp.assembler.UserAssembler;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.UserEntity;
import com.example.luckydrawconfig.exception.Exception;
import com.example.luckydrawconfig.util.AccessGithubToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class UserVerifyGithubExe {
    private  final UserGateWay userGateWay;
    public UserVO execute(String code) {
       String loginUserName= AccessGithubToken.getUserInfo(code);
       UserEntity userEntity= userGateWay.findByGithub(loginUserName);
       if(Objects.isNull(userEntity))
       {
           throw new Exception("Github账号暂未于LuckyDraw账号绑定！请先注册账号");
       }
       return UserAssembler.toUserVo(userEntity);
    }
}
