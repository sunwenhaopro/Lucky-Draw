package com.example.luckydomain.user;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserEntity {

    private Long id;


    private UserName username;


    private Password password;

    private String name;

    private String sex;

    private String phone;
    private String birth;
    private String avatar;
    private String github;
    private String address;


    private String mail;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;




}
