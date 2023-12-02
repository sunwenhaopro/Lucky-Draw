package com.example.luckydrawconfig.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 *@author CtrlCver
 *@data 2023/8/14
 *@description:
 */
@Getter
@ToString
@Setter
public class ResultInfo implements Serializable {
    private Boolean result;
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    public ResultInfo(Boolean result,Integer code,String message)
    {
        this.code=code;
        this.result=result;
        this.message=message;
    }
}
