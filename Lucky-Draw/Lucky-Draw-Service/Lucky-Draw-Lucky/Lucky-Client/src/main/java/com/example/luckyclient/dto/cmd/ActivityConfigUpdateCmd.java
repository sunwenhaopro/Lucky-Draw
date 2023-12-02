package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import java.util.List;

/**
 * @author J3（about：https://j3code.cn）
 * @package cn.j3code.luckyclient.dto
 * @createTime 2022/12/4 - 22:32
 * @description
 */
@Data
public class ActivityConfigUpdateCmd extends Command {

    private ActivityUpdateCmd activityUpdateCmd;

    private RuleUpdateCmd ruleUpdateCmd;

    private List<AwardUpdateCmd> awardUpdateCmdList;
}
