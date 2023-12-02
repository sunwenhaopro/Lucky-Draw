package com.example.luckydomain.award;


import com.example.luckydrawconfig.exception.Exception;
import lombok.Getter;


@Getter
public class AwardNumber {

    private Integer number;

    public AwardNumber(Integer number) {

        if (number < 0) {
            throw new Exception("奖项数量不合法，需大于等于 0");
        }

        this.number = number;
    }

}
