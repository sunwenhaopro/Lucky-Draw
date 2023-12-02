package com.example.luckyapp.activityrule.command;

import cn.hutool.core.collection.CollectionUtil;
import com.example.luckyapp.assembler.ActivityRuleAssembler;
import com.example.luckyclient.dto.cmd.ActivityRuleAddCmd;
import com.example.luckyclient.dto.data.ActivityRuleVO;
import com.example.luckydomain.activityrule.ActivityRuleEntity;
import com.example.luckydomain.gateway.ActivityRuleGateway;
import com.example.luckydrawconfig.exception.Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleAddCmdExe {

    private final ActivityRuleGateway activityRuleGateway;

    public ActivityRuleVO execute(ActivityRuleAddCmd cmd) {
        ActivityRuleEntity entity = activityRuleGateway.save(ActivityRuleAssembler.toAddEntity(cmd));
        return ActivityRuleAssembler.toActivityRuleVO(entity);
    }

    public List<ActivityRuleVO> execute(List<ActivityRuleAddCmd> cmdList) {
        if (CollectionUtil.isEmpty(cmdList)) {
            throw new Exception("数据有误！");
        }

        List<ActivityRuleVO> result = new ArrayList<>();
        for (ActivityRuleAddCmd addCmd : cmdList) {
            result.add(execute(addCmd));
        }

        return result;
    }
}
