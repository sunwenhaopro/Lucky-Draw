package com.example.luckyapp.service;


import com.example.luckyapp.activityrule.command.ActivityRuleAddCmdExe;
import com.example.luckyapp.activityrule.query.ActivityRuleListByParamQueryExe;
import com.example.luckyclient.api.IActivityRuleService;
import com.example.luckyclient.dto.cmd.ActivityRuleAddCmd;
import com.example.luckyclient.dto.data.ActivityRuleVO;
import com.example.luckyclient.dto.query.ActivityRuleListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ActivityRuleServiceImpl  implements IActivityRuleService {

    private final ActivityRuleAddCmdExe activityRuleAddCmdExe;
    private final ActivityRuleListByParamQueryExe activityRuleListByParamQueryExe;

    @Override
    public ActivityRuleVO add(ActivityRuleAddCmd cmd) {
        return activityRuleAddCmdExe.execute(cmd);
    }

    @Override
    public List<ActivityRuleVO> list(ActivityRuleListByParamQuery query) {
        return activityRuleListByParamQueryExe.execute(query);
    }
}
