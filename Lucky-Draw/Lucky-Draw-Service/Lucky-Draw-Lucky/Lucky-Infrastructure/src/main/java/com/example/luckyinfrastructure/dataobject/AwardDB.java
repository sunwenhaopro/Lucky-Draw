package com.example.luckyinfrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName bld_award
 */
@TableName(value ="bld_award")
@Data
public class AwardDB implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 奖品名称
     */
    private Long prizeId;
    @TableField(exist = false)
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
     * 概率
     */
    private Double probability;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     *
     */
    private String updater;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}