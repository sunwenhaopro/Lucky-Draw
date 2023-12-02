package com.example.luckyinfrastructure.gateway.impl;

import com.alibaba.cola.exception.SysException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckydomain.gateway.UserGateWay;
import com.example.luckydomain.user.UserEntity;
import com.example.luckyinfrastructure.convertor.UserConvertor;
import com.example.luckyinfrastructure.dataobject.UserDB;
import com.example.luckyinfrastructure.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class UserGateWayImpl implements UserGateWay {
    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserEntity entity) {
        UserDB userDB= UserConvertor.toUserDB(entity);
        System.out.println(userDB);
        int insert= userMapper.insert(userDB);
        if(insert <= 0){
            throw new SysException("注册失败");
        }
        return UserConvertor.toUserEntity(userDB);
    }
    @Override
    public Boolean updateById(UserEntity userEntity) {
        UserDB userDB=UserConvertor.updateToUserDB(userEntity);
        int update = userMapper.updateById(userDB);
        if (update <= 0) {
            throw new SysException("修改失败！");
        }
        return update == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public UserEntity findByMail(String mail) {
        UserDB userDB=userMapper.findByMail(mail);
        if(Objects.isNull(userDB))
        {
            return null;
        }
        return UserConvertor.toUserEntity(userDB);
    }

    @Override
    public UserEntity findByGithub(String loginUserName) {
        UserDB userDB=userMapper.findByGithub(loginUserName);
        if(Objects.isNull(userDB))
        {
            return null;
        }
        return UserConvertor.toUserEntity(userDB);
    }

    @Override
    public UserEntity findByUserName(Long id, String username) {
        UserDB userDB = userMapper.findByUserName(id, username);
        if (Objects.isNull(userDB)) {
            return null;
        }
        return UserConvertor.toUserEntity(userDB);
    }

    @Override
    public IPage<UserEntity> listByParamQuery(UserListByParamQuery query) {

            IPage<UserDB> userDBIPage = userMapper.listByParamQuery(new Page<UserEntity>(query.getPageIndex(), query.getPageSize()), query);

            return userDBIPage.convert(UserConvertor::toUserEntity);
        }
    }

