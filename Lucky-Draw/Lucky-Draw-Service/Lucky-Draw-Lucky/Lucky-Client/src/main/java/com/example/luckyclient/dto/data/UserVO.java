package com.example.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    private Long id;


    private String username;



    private String name;


    private String mail;

    private String sex;

    private String phone;
    private String birth;
    private String avatar;
    private String address;
    private String github;

    private LocalDateTime createTime;


    private LocalDateTime updateTime;

}
