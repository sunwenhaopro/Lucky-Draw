package com.example.luckyapp.user.command;

import com.example.luckyapp.assembler.UserAssembler;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.UserEntity;
import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UserRefreshTokenExe {
    private final UserGateWay userGateWay;
    public UserVO execute() {
        String username= SecurityUtil.getUsername();
        UserEntity  userEntity= userGateWay.findByUserName(null, username);
        return UserAssembler.toUserVo(userEntity);
    }
}
