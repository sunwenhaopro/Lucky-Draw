package com.example.luckyclient.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author J3（about：https://j3code.cn）
 * @package cn.j3code.luckyclient.dto.data
 * @createTime 2022/12/2 - 22:28
 * @description
 */
@Data
public class AwardVO {

    /**
     *
     */
    private Long id;

    /**
     * 奖品名称
     */
    private Long prizeId;
    private String prizeName;
    private Long activityId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 奖项名称
     */
    private String awardName;

    private String awardPhoto;
    /**
     * 概率（1，0.001）
     */
    private Double probability;

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
