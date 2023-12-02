package com.example.luckydomain.gateway;


import com.example.luckyclient.dto.query.ActivityRuleListByParamQuery;
import com.example.luckydomain.activityrule.ActivityRuleEntity;

import java.util.List;


public interface ActivityRuleGateway {

    ActivityRuleEntity save(ActivityRuleEntity entity);

    List<ActivityRuleEntity> list(ActivityRuleListByParamQuery query);

    void deleteByActivityId(Long activityId);
}
