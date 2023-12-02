package com.example.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class ActivityListByParamQuery extends PageQuery {

    private Long id;

    private String activityName;

    private String creator;
    /**
     * 开始时间
     */

    private LocalDateTime startTime;

    /**
     * 结束时间
     */

    private LocalDateTime endTime;

    /**
     * 活动状态（0、1、2）
     */
    private Integer status;

}
