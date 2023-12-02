package com.example.luckydomain.award;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author J3（about：https://j3code.cn）
 * @package cn.j3code.luckydomain.award
 * @createTime 2022/12/2 - 22:25
 * @description
 */
@Entity
@Data
public class AwardEntity {

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
     * 数量(这个属性肯定是要进行判断的，只有符合的值才能设置)
     */
    private AwardNumber number;

    /**
     * 奖项名称
     */
    private String awardName;

    private String awardPhoto;
    /**
     * 概率
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


    /**
     * 判断该奖项是否是一个奖品，也即该奖项是否中奖
     *
     * @return
     */
    public Boolean isPrize() {
        return !"0".equals(this.prizeId.toString());
    }
}
