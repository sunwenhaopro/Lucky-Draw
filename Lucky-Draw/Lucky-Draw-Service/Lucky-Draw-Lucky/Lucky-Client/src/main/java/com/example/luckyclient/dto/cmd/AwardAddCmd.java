package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AwardAddCmd extends Command {

    /**
     * 奖品名称
     */
    private Long prizeId;

    private Long activityId;

    private String awardPhoto;
    /**
     * 数量
     */
    @NotNull(message = "数量不为空")
    private Integer number;

    /**
     * 奖项名称
     */
    @NotNull(message = "奖项名称不为空")
    private String awardName;

    /**
     * 概率
     */
    @NotNull(message = "概率不为空")
    private Double probability;
}
