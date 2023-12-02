package com.example.luckydrawconfig.vo;

import lombok.Getter;


@Getter
public enum ExceptionEnum {

    ADD_ERROR(FailInfo.DEFAULT_CODE, "保存数据失败！"),

    UPDATE_ERROR(FailInfo.DEFAULT_CODE, "保存数据失败！"),

    ;
    private Integer code;

    private String description;

    ExceptionEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }

}
