package com.example.luckyclient.api;


import com.example.luckyclient.dto.cmd.ActivityRuleAddCmd;
import com.example.luckyclient.dto.data.ActivityRuleVO;
import com.example.luckyclient.dto.query.ActivityRuleListByParamQuery;

import java.util.List;

public interface IActivityRuleService {

    ActivityRuleVO add(ActivityRuleAddCmd cmd);

    List<ActivityRuleVO> list(ActivityRuleListByParamQuery query);
}
