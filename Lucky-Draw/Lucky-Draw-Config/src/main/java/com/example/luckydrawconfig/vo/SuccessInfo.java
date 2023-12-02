package com.example.luckydrawconfig.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString(callSuper = true)
public class SuccessInfo extends ResultInfo {
    private static final Integer DEFAULT_CODE = 200;
    private static final String DEFAULT_MESSAGE = "操作成功";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public SuccessInfo(Object data) {
        super(true, DEFAULT_CODE, DEFAULT_MESSAGE);
        this.data = data;
    }

}
