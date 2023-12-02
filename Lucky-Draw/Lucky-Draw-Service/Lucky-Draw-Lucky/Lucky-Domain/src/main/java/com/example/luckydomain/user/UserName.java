package com.example.luckydomain.user;

import com.example.luckydrawconfig.exception.Exception;
import lombok.Getter;

import java.util.Objects;

@Getter
public class UserName {
    private String userName;
    public UserName(String userName){
        if(Objects.isNull(userName))
        {
            throw new Exception("账号不能为空");
        }
        this.userName=userName;
    }
}
