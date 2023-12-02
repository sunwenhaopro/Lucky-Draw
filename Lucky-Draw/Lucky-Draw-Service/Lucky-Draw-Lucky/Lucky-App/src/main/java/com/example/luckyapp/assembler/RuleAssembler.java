package com.example.luckyapp.assembler;

import com.example.luckyclient.dto.cmd.RuleAddCmd;
import com.example.luckyclient.dto.cmd.RuleUpdateCmd;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckydomain.rule.MaxJoinNumber;
import com.example.luckydomain.rule.MaxWinNumber;
import com.example.luckydomain.rule.RuleEntity;


import java.time.LocalDateTime;

public class RuleAssembler {

    public static RuleEntity toRuleEntity(RuleAddCmd cmd) {
        RuleEntity ruleEntity=new RuleEntity();
        ruleEntity.setRuleName(cmd.getRuleName());
        ruleEntity.setMaxJoinNumber(new MaxJoinNumber(cmd.getMaxJoinNumber()));
        ruleEntity.setMaxWinNumber(new MaxWinNumber(cmd.getMaxWinningNumber()));
        ruleEntity.setCreateTime(LocalDateTime.now());
        ruleEntity.setCreator(cmd.getCreator());
        ruleEntity.setUpdateTime(LocalDateTime.now());
        return  ruleEntity;

    }

    public static RuleVO toRuleVO(RuleEntity ruleEntity) {
        RuleVO ruleVO=new RuleVO();
        ruleVO.setId(ruleEntity.getId());
        ruleVO.setRuleName(ruleEntity.getRuleName());
        ruleVO.setMaxJoinNumber(ruleEntity.getMaxJoinNumber().getNumber());
        ruleVO.setMaxWinningNumber(ruleEntity.getMaxWinNumber().getNumber());
        ruleVO.setCreateTime(ruleEntity.getCreateTime());
        ruleVO.setCreator(ruleEntity.getCreator());
        ruleVO.setUpdateTime(ruleEntity.getUpdateTime());
       return ruleVO;

    }
    public  static RuleEntity toRuleEntity(RuleUpdateCmd cmd)
    {
        RuleEntity ruleEntity=new RuleEntity();
        ruleEntity.setId(cmd.getId());
        ruleEntity.setRuleName(cmd.getRuleName());
        ruleEntity.setMaxJoinNumber(new MaxJoinNumber(cmd.getMaxJoinNumber()));
        ruleEntity.setMaxWinNumber(new MaxWinNumber(cmd.getMaxWinningNumber()));
        ruleEntity.setUpdateTime(LocalDateTime.now());
        return  ruleEntity;
    }
}
