package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;


@Data
public class ActivityRuleUpdateCmd extends Command {

    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 规则id
     */
    private Long ruleId;
}
