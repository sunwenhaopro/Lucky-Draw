package com.example.luckyapp.assembler;
import com.example.luckyclient.dto.cmd.ActivityRuleAddCmd;
import com.example.luckyclient.dto.data.ActivityRuleVO;
import com.example.luckydomain.activityrule.ActivityRuleEntity;
import com.example.luckydrawconfig.util.SecurityUtil;

import java.time.LocalDateTime;


public class ActivityRuleAssembler {
    public static ActivityRuleEntity toAddEntity(ActivityRuleAddCmd cmd) {
        ActivityRuleEntity activityRuleEntity = new ActivityRuleEntity();
        activityRuleEntity.setActivityId(cmd.getActivityId());
        activityRuleEntity.setRuleId(cmd.getRuleId());
        activityRuleEntity.setCreateTime(LocalDateTime.now());
        activityRuleEntity.setCreator(SecurityUtil.getUsername());
        activityRuleEntity.setUpdateTime(LocalDateTime.now());
        activityRuleEntity.setUpdater(SecurityUtil.getUsername());

        return activityRuleEntity;
    }

    public static ActivityRuleVO toActivityRuleVO(ActivityRuleEntity entity) {
        ActivityRuleVO activityRuleVO = new ActivityRuleVO();
        activityRuleVO.setId(entity.getId());
        activityRuleVO.setActivityId(entity.getActivityId());
        activityRuleVO.setRuleId(entity.getRuleId());
        activityRuleVO.setCreateTime(entity.getCreateTime());
        activityRuleVO.setCreator(entity.getCreator());
        activityRuleVO.setUpdateTime(entity.getUpdateTime());
        activityRuleVO.setUpdater(entity.getUpdater());

        return activityRuleVO;
    }
}
