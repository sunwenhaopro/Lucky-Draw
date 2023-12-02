package com.example.luckyinfrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName bld_result
 */
@TableName(value ="bld_result")
@Data
public class ResultDB implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private String awardName;

    /**
     * 
     */
    private String prizeName;

    /**
     * 
     */
    private String activityName;

    /**
     * 
     */
    private String isDraw;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}