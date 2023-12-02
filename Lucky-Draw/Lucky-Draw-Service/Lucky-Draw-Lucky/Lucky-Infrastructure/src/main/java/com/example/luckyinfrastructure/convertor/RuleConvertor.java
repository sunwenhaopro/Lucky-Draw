package com.example.luckyinfrastructure.convertor;
import com.example.luckydomain.rule.MaxWinNumber;

import com.example.luckydomain.rule.MaxJoinNumber;
import com.example.luckydomain.rule.RuleEntity;
import com.example.luckyinfrastructure.dataobject.RuleDB;

import java.time.LocalDateTime;

public class RuleConvertor {
    public static RuleDB toRuleDB(RuleEntity entity) {
        RuleDB ruleDB=new RuleDB();
        ruleDB.setId(entity.getId());
        ruleDB.setRuleName(entity.getRuleName());
        ruleDB.setMaxJoinNumber(entity.getMaxJoinNumber().getNumber());
        ruleDB.setMaxWinningNumber(entity.getMaxWinNumber().getNumber());
        ruleDB.setCreateTime(LocalDateTime.now());
        ruleDB.setCreator(entity.getCreator());
        ruleDB.setUpdateTime(LocalDateTime.now());
       return ruleDB;
    }

    public static RuleEntity toRuleEntity(RuleDB ruleDB) {
          RuleEntity ruleEntity=new RuleEntity();
          ruleEntity.setId(ruleDB.getId());
          ruleEntity.setRuleName(ruleDB.getRuleName());
          ruleEntity.setMaxJoinNumber(new MaxJoinNumber(ruleDB.getMaxJoinNumber()));
          ruleEntity.setMaxWinNumber(new MaxWinNumber(ruleDB.getMaxWinningNumber()));
          ruleEntity.setCreateTime(ruleDB.getCreateTime());
          ruleEntity.setCreator(ruleDB.getCreator());
          ruleEntity.setUpdateTime(ruleDB.getUpdateTime());
          return ruleEntity;



    }
}
