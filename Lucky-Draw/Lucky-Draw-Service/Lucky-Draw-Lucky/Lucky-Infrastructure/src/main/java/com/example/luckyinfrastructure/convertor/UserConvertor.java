package com.example.luckyinfrastructure.convertor;

import com.example.luckydomain.user.Password;
import com.example.luckydomain.user.UserEntity;
import com.example.luckydomain.user.UserName;
import com.example.luckyinfrastructure.dataobject.UserDB;

public class UserConvertor {
    public static UserDB toUserDB(UserEntity userEntity){
        UserDB userDB=new UserDB();
        userDB.setId(userEntity.getId());
        userDB.setUsername(userEntity.getUsername().getUserName());
        userDB.setPassword(userEntity.getPassword().getEncryptionPassWord().getPassword());
        userDB.setName(userEntity.getName());
        userDB.setMail(userEntity.getMail());
        userDB.setCreateTime(userEntity.getCreateTime());
        userDB.setUpdateTime(userEntity.getUpdateTime());
        return userDB;
    }
    public static UserDB updateToUserDB(UserEntity userEntity){
        UserDB userDB=new UserDB();
        userDB.setId(userEntity.getId());
        userDB.setName(userEntity.getName());
        userDB.setMail(userEntity.getMail());
        userDB.setSex(userEntity.getSex());
        userDB.setPhone(userEntity.getPhone());
        userDB.setBirth(userEntity.getBirth());
        userDB.setAvatar(userEntity.getAvatar());
        userDB.setAddress(userEntity.getAddress());
        userDB.setUpdateTime(userEntity.getUpdateTime());
        return userDB;
    }
    public static UserEntity toUserEntity(UserDB userDB){
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userDB.getId());
        userEntity.setUsername(new UserName(userDB.getUsername()));
        userEntity.setPassword(new Password(new Password.EncryptionPassWord(userDB.getPassword())));
        userEntity.setName(userDB.getName());
        userEntity.setMail(userDB.getMail());
        userEntity.setSex(userDB.getSex());
        userEntity.setPhone(userDB.getPhone());
        userEntity.setBirth(userDB.getBirth());
        userEntity.setAvatar(userDB.getAvatar());
        userEntity.setGithub(userDB.getGithub());
        userEntity.setAddress(userDB.getAddress());
        userEntity.setCreateTime(userDB.getCreateTime());
        userEntity.setUpdateTime(userDB.getUpdateTime());
        return userEntity;
    }

}
