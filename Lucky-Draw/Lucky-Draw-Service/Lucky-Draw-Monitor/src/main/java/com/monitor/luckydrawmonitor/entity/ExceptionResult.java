package com.monitor.luckydrawmonitor.entity;

import lombok.Data;


@Data
public class ExceptionResult {
    private String path;
    private String description;
    private String time;
}
