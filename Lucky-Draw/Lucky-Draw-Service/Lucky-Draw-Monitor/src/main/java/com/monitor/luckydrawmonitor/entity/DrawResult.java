package com.monitor.luckydrawmonitor.entity;

import lombok.Data;

@Data
public class DrawResult {
    private String awardName;
    private String prizeName;
    private String activityName;
    private Boolean isDraw;
    private Long id;
}
