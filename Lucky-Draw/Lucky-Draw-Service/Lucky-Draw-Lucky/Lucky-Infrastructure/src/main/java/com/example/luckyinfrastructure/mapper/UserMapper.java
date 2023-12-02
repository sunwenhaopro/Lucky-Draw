package com.example.luckyinfrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.UserListByParamQuery;
import com.example.luckydomain.user.UserEntity;
import com.example.luckyinfrastructure.dataobject.UserDB;

import org.apache.ibatis.annotations.Param;


public interface UserMapper extends BaseMapper<UserDB> {

    UserDB findByUserName(@Param("id")Long id,@Param("username") String username);

    IPage<UserDB> listByParamQuery(@Param("page") Page<UserEntity> userEntityPage, @Param("query") UserListByParamQuery query);


    UserDB findByMail(@Param("mail")String mail);
    UserDB findByGithub(@Param("github")String github);
}




