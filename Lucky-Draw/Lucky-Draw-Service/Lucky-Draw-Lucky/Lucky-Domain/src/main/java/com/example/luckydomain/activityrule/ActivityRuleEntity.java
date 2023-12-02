package com.example.luckydomain.activityrule;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class ActivityRuleEntity {

    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    private LocalDateTime updateTime;

    /**
     *
     */
    private String updater;

}
