package com.example.luckydomain.rule;

import com.example.luckydrawconfig.exception.Exception;
import lombok.Getter;

@Getter
public class MaxJoinNumber {
    private Integer number;
    public MaxJoinNumber(Integer number){
        if(number<1)
        {
            throw new Exception("参与次数必须大于一");
        }
        this.number=number;
    }
}