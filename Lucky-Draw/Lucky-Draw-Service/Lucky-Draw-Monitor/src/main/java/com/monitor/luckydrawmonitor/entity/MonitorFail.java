package com.monitor.luckydrawmonitor.entity;

import lombok.Data;

@Data
public class MonitorFail {
    private String expection;
    private Boolean result;
    private Integer code;
    private String message;
}
