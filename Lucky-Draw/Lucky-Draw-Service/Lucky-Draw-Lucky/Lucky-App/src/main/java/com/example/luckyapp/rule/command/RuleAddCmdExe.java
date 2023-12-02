package com.example.luckyapp.rule.command;


import com.example.luckyapp.assembler.RuleAssembler;
import com.example.luckyclient.dto.cmd.RuleAddCmd;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckydomain.gateway.RuleGateWay;
import com.example.luckydomain.rule.RuleEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RuleAddCmdExe  {
    private final RuleGateWay ruleGateWay;

    public RuleVO execute(RuleAddCmd cmd){
        RuleEntity ruleEntity= ruleGateWay.add(RuleAssembler.toRuleEntity(cmd));
        return RuleAssembler.toRuleVO(ruleEntity);
    }
}
