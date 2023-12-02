package com.example.luckydomain.gateway;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.query.RuleListByParamQuery;
import com.example.luckydomain.rule.RuleEntity;

public interface RuleGateWay {
    RuleEntity add(RuleEntity entity);

    RuleEntity update(RuleEntity ruleEntity);

    IPage<RuleEntity> page(RuleListByParamQuery query);

    RuleEntity one(Long id);

}
