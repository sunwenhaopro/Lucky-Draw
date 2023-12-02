package com.example.luckyapp.rule.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.assembler.RuleAssembler;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckyclient.dto.query.RuleListByParamQuery;
import com.example.luckydomain.gateway.RuleGateWay;
import com.example.luckydomain.rule.RuleEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RuleListByParamQueryExe {
    private final RuleGateWay ruleGateWay;
    public IPage<RuleVO> execute(RuleListByParamQuery query)
    {
         IPage<RuleEntity> page=  ruleGateWay.page(query);
         return page.convert(RuleAssembler::toRuleVO);
    }
}
