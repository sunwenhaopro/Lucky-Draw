package com.example.luckyapp.user.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.assembler.UserAssembler;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UserListByParamQueryExe {
    private final UserGateWay userGateWay;
    public  IPage<UserVO> execute(UserListByParamQuery query){
     IPage<UserEntity> userEntityIPage= userGateWay.listByParamQuery(query);
        return userEntityIPage.convert(UserAssembler::toUserVo);
    }
}
