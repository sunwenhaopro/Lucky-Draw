package com.example.luckydrawconfig.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString(callSuper = true)
@Getter
public class FailInfo extends ResultInfo{
    public static final Integer DEFAULT_CODE =400 ;
    private static final String DEFAULT_MESSAGE="操作失败";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expection;
    public FailInfo(String expection) {
        super(false, DEFAULT_CODE, DEFAULT_MESSAGE);
        this.expection = expection;
    }
    public FailInfo(Integer code,String expection) {
        super(false, code, DEFAULT_MESSAGE);
        this.expection = expection;
    }
}
