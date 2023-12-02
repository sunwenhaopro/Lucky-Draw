package com.example.luckyapp.rule.query;

import com.example.luckyapp.assembler.RuleAssembler;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckydomain.gateway.RuleGateWay;
import com.example.luckydomain.rule.RuleEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class RuleByIdQuery {
    private final RuleGateWay ruleGateWay;
    public RuleVO execute(Long id)
    {
        RuleEntity ruleEntity= ruleGateWay.one(id);
        return RuleAssembler.toRuleVO(ruleEntity);
    }
}
