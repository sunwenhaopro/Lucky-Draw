package com.example.luckyapp.user.command;

import com.example.luckyapp.assembler.UserAssembler;
import com.example.luckyclient.dto.cmd.UserUpdateCmd;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class UserUpdateCmdExe {
    private final UserGateWay userGateWay;
    public Boolean execute(UserUpdateCmd cmd){
        UserEntity userEntity = UserAssembler.toAddEntity(cmd);
        return userGateWay.updateById(userEntity);
    }
}
