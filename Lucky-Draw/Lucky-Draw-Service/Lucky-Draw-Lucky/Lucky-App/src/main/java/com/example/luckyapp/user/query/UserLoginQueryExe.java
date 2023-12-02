package com.example.luckyapp.user.query;

import com.example.luckyapp.assembler.UserAssembler;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckyclient.dto.query.UserLoginQuery;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.Password;
import com.example.luckydomain.user.UserEntity;
import com.example.luckydrawconfig.exception.Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class UserLoginQueryExe {
    private final UserGateWay userGateWay;
    public UserVO execute(UserLoginQuery query){
        UserEntity loadUserEntity=  userGateWay.findByUserName(null,query.getUsername());
        if(Objects.isNull(loadUserEntity)){
            throw new Exception("账号不存在");
        }
        if(!loadUserEntity.getPassword().getEncryptionPassWord().getPassword().equals(Password.getEncryptionPassWord(query.getPassword())))
        {
            throw new Exception("密码错误");
        }
        return UserAssembler.toUserVo(loadUserEntity);
    }
}
