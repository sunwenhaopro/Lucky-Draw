package com.example.luckyapp.assembler;


import com.example.luckyclient.dto.cmd.ActivityAddCmd;
import com.example.luckyclient.dto.cmd.ActivityUpdateCmd;
import com.example.luckyclient.dto.data.ActivityVO;
import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.activity.ActivityTime;
import com.example.luckydrawconfig.util.SecurityUtil;

import java.time.LocalDateTime;

public class ActivityAssembler {
    public static ActivityEntity toAddEntity(ActivityAddCmd cmd) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityName(cmd.getActivityName());
        activityEntity.setSurface(cmd.getSurface());
        activityEntity.setActivityTime(new ActivityTime(cmd.getStartTime(), cmd.getEndTime()));
        activityEntity.setDescribe(cmd.getDescribe());
        activityEntity.setCreateTime(LocalDateTime.now());
        activityEntity.setCreator(SecurityUtil.getUsername());
        activityEntity.setUpdateTime(LocalDateTime.now());
        activityEntity.setUpdater(SecurityUtil.getUsername());
        return activityEntity;
    }

    public static ActivityVO toActivityVO(ActivityEntity entity) {
        ActivityVO activityVO = new ActivityVO();
        activityVO.setId(entity.getId());
        activityVO.setSurface(entity.getSurface());
        activityVO.setActivityName(entity.getActivityName());
        activityVO.setStartTime(entity.getActivityTime().getStartTime());
        activityVO.setEndTime(entity.getActivityTime().getEndTime());
        activityVO.setDescribe(entity.getDescribe());
        activityVO.setCreateTime(entity.getCreateTime());
        activityVO.setCreator(entity.getCreator());
        activityVO.setUpdateTime(entity.getUpdateTime());
        activityVO.setUpdater(entity.getUpdater());
        activityVO.setStatus(entity.getActivityTime().getStatus().getValue());
        return activityVO;
    }

    public static ActivityEntity toUpdateEntity(ActivityUpdateCmd cmd) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(cmd.getId());
        activityEntity.setActivityName(cmd.getActivityName());
        activityEntity.setActivityTime(new ActivityTime(cmd.getStartTime(), cmd.getEndTime()));
        activityEntity.setDescribe(cmd.getDescribe());
        activityEntity.setUpdateTime(LocalDateTime.now());
        activityEntity.setUpdater(SecurityUtil.getUsername());

        return activityEntity;
    }

    public static ActivityAddCmd toActivityAddCmd(ActivityVO activityVO) {
        ActivityAddCmd activityAddCmd = new ActivityAddCmd();
        activityAddCmd.setActivityName(activityVO.getActivityName());
        activityAddCmd.setStartTime(activityVO.getStartTime());
        activityAddCmd.setEndTime(activityVO.getEndTime());
        activityAddCmd.setDescribe(activityVO.getDescribe());

        return activityAddCmd;
    }
}
