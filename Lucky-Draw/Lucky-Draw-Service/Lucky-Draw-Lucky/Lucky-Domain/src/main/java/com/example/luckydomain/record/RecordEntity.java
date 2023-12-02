package com.example.luckydomain.record;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class RecordEntity {
    /**
     *
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long activityId;

    private String activityName;

    /**
     * 奖项id
     */
    private Long awardId;

    private String awardName;

    private String prizeName;

    private Integer prizeType;


    /**
     * 是否中奖：0未中奖，1中奖
     */
    private Integer isWinning;


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

    private Integer state;

}
