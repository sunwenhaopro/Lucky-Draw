package com.example.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;


@Data
public class RecordListByParamQuery extends PageQuery {

    private Long recordId;

    private Long userId;

    private Long activityId;

    /**
     * true：中奖，false：未中奖
     */
    private Boolean winTheLottery;


    private Integer status;
}
