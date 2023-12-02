package com.example.luckyapp.assembler;

import com.example.luckyclient.dto.cmd.UserUpdateCmd;
import com.example.luckyclient.dto.data.UserVO;
import com.example.luckyclient.dto.cmd.UserRegisterCmd;
import com.example.luckydomain.user.Password;
import com.example.luckydomain.user.UserEntity;
import com.example.luckydomain.user.UserName;

import java.time.LocalDateTime;

public class UserAssembler {
    public static UserEntity toAddEntity(UserRegisterCmd cmd){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(new UserName(cmd.getUsername()));
        userEntity.setPassword(new Password(new Password.EncryptionPassWord(Password.getEncryptionPassWord(cmd.getPassword()))));
        userEntity.setName(cmd.getName());
        userEntity.setMail(cmd.getMail());
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setUpdateTime(LocalDateTime.now());
        return userEntity;
    }
    public static UserVO toUserVo(UserEntity userEntity){
        UserVO userVo=new UserVO();
        userVo.setId(userEntity.getId());
        userVo.setUsername(userEntity.getUsername().getUserName());
        userVo.setName(userEntity.getName());
        userVo.setMail(userEntity.getMail());
        userVo.setSex(userEntity.getSex());
        userVo.setPhone(userEntity.getPhone());
        userVo.setBirth(userEntity.getBirth());
        userVo.setAvatar(userEntity.getAvatar());
        userVo.setGithub(userEntity.getGithub());
        userVo.setAddress(userEntity.getAddress());
        userVo.setCreateTime(userEntity.getCreateTime());
        userVo.setUpdateTime(userEntity.getUpdateTime());
        return userVo;
    }
    public static UserEntity toAddEntity(UserUpdateCmd cmd){
        UserEntity userEntity=new UserEntity();
        userEntity.setId(cmd.getId());
        userEntity.setName(cmd.getName());
        userEntity.setSex(cmd.getSex());
        userEntity.setPhone(cmd.getPhone());
        userEntity.setBirth(cmd.getBirth());
        userEntity.setAvatar(cmd.getAvatar());
        userEntity.setAddress(cmd.getAddress());
        userEntity.setUpdateTime(LocalDateTime.now());
        return userEntity;
    }
}
