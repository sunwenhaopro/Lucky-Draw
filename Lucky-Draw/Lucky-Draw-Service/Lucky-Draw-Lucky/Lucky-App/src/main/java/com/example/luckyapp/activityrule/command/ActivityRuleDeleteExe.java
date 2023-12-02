package com.example.luckyapp.activityrule.command;


import com.example.luckydomain.gateway.ActivityRuleGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleDeleteExe {

    private final ActivityRuleGateway activityRuleGateway;

    public void execute(Long activityId) {
        activityRuleGateway.deleteByActivityId(activityId);
    }
}
