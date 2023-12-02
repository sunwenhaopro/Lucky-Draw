package com.example.luckydomain.rule;

import com.example.luckydrawconfig.exception.Exception;
import lombok.Getter;

@Getter
public class MaxWinNumber {
    private Integer number;
    public MaxWinNumber(Integer number)
    {
        if(number<1)
        {
            throw new Exception("至少设置一个人获奖！");
        }
        this.number=number;
    }
}
