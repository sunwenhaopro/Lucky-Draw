package com.example.luckyclient.dto.data;

import lombok.Data;

import java.util.List;

/**
 * @author J3（about：https://j3code.cn）
 * @package cn.j3code.luckyclient.dto.data
 * @createTime 2022/12/4 - 22:31
 * @description
 */
@Data
public class ActivityConfigVO {

    private ActivityVO activityVO;

    private RuleVO ruleVO;

    private List<AwardVO> awardVOList;
}
