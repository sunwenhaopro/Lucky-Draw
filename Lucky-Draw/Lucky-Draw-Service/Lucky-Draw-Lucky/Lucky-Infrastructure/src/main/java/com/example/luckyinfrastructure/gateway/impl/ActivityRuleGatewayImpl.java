package com.example.luckyinfrastructure.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.ActivityRuleListByParamQuery;
import com.example.luckydomain.activityrule.ActivityRuleEntity;
import com.example.luckydomain.gateway.ActivityRuleGateway;
import com.example.luckydrawconfig.util.AssertUtil;
import com.example.luckyinfrastructure.convertor.ActivityRuleConvertor;
import com.example.luckyinfrastructure.dataobject.ActivityRuleDB;
import com.example.luckyinfrastructure.mapper.ActivityRuleMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@AllArgsConstructor
public class ActivityRuleGatewayImpl implements ActivityRuleGateway {

    private final ActivityRuleMapper activityRuleMapper;

    @Override
    public ActivityRuleEntity save(ActivityRuleEntity entity) {

        ActivityRuleDB activityRuleDB = ActivityRuleConvertor.toActivityRuleDB(entity);

        AssertUtil.isTrue(activityRuleMapper.insert(activityRuleDB) <= 0, "保存失败");

        return ActivityRuleConvertor.toEntity(activityRuleDB);
    }

    @Override
    public List<ActivityRuleEntity> list(ActivityRuleListByParamQuery query) {
        List<ActivityRuleDB> list = activityRuleMapper.list(query);
        if (CollectionUtil.isEmpty(list)){
            return new ArrayList<>();
        }
        return new Page<ActivityRuleDB>()
                .setRecords(list)
                .convert(ActivityRuleConvertor::toEntity)
                .getRecords();
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        activityRuleMapper.deleteByActivityId(activityId);
    }
}
