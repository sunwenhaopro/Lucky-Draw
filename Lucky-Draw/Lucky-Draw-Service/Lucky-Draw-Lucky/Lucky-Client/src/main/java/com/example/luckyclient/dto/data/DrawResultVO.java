package com.example.luckyclient.dto.data;

import lombok.Data;

@Data
public class DrawResultVO {
    private String awardName;
    private String prizeName;
    private String activityName;
    private Boolean isDraw;
    private Long id;
}
