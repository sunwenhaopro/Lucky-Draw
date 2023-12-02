package com.example.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;


@Data
public class AwardListByParamQuery extends PageQuery {

    private Long id;

    private Long activityId;

    private String activityName;

    private String awardName;
}
