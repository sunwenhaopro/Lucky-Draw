package com.example.luckyinfrastructure.gateway.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.RuleListByParamQuery;
import com.example.luckydomain.gateway.RuleGateWay;
import com.example.luckydomain.rule.RuleEntity;
import com.example.luckydrawconfig.exception.Exception;
import com.example.luckyinfrastructure.convertor.RuleConvertor;
import com.example.luckyinfrastructure.dataobject.RuleDB;
import com.example.luckyinfrastructure.mapper.RuleMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class RuleGateWayImpl implements RuleGateWay {
    private final RuleMapper ruleMapper;
    @Override
    public RuleEntity add(RuleEntity entity) {
        RuleDB ruleDB= RuleConvertor.toRuleDB(entity);
        int insert=ruleMapper.insert(ruleDB);
        if(insert<=0)
        {
            throw new Exception("添加失败");
        }
        return RuleConvertor.toRuleEntity(ruleDB);
    }

    @Override
    public RuleEntity update(RuleEntity ruleEntity) {
        RuleDB ruleDB=RuleConvertor.toRuleDB(ruleEntity);
        int update=ruleMapper.updateById(ruleDB);
        if(update<=0)
        {
            throw new Exception("更新失败");
        }
        return RuleConvertor.toRuleEntity(ruleDB);
    }

    @Override
    public IPage<RuleEntity> page(RuleListByParamQuery query) {
        IPage<RuleDB> page=ruleMapper.page(new Page<RuleDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(RuleConvertor::toRuleEntity);
    }

    @Override
    public RuleEntity one(Long id) {
        RuleDB ruleDB=ruleMapper.findOneById(id);
        return RuleConvertor.toRuleEntity(ruleDB);
    }
}
