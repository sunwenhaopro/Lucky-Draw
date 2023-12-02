package com.example.luckyapp.rule.command;

import com.example.luckyapp.assembler.RuleAssembler;
import com.example.luckyclient.dto.cmd.RuleUpdateCmd;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckydomain.gateway.RuleGateWay;
import com.example.luckydomain.rule.RuleEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RuleUpdateCmdExe {
    private final RuleGateWay ruleGateWay;
    public RuleVO execute(RuleUpdateCmd cmd)
    {
       RuleEntity entity=ruleGateWay.update(RuleAssembler.toRuleEntity(cmd));
       return RuleAssembler.toRuleVO(entity) ;
    }
}
