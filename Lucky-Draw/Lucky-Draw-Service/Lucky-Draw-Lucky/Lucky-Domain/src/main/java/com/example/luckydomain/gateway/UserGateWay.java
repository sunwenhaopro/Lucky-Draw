package com.example.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckydomain.user.UserEntity;

public interface UserGateWay {
    UserEntity save(UserEntity entity);

    UserEntity findByUserName(Long id, String username);

    IPage<UserEntity> listByParamQuery(UserListByParamQuery query);

    Boolean updateById(UserEntity userEntity);

    UserEntity findByMail(String mail);

    UserEntity findByGithub(String loginUserName);
}
