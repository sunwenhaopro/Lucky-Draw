package com.example.luckyinfrastructure.convertor;

import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.activity.ActivityTime;
import com.example.luckyinfrastructure.dataobject.ActivityDB;


public class ActivityConvertor {
    public static ActivityDB toActivityDB(ActivityEntity entity) {
        ActivityDB activityDB = new ActivityDB();
        activityDB.setId(entity.getId());
        activityDB.setSurface(entity.getSurface());
        activityDB.setActivityName(entity.getActivityName());
        activityDB.setStartTime(entity.getActivityTime().getStartTime());
        activityDB.setEndTime(entity.getActivityTime().getEndTime());
        activityDB.setDescribe(entity.getDescribe());
        activityDB.setCreateTime(entity.getCreateTime());
        activityDB.setCreator(entity.getCreator());
        activityDB.setUpdateTime(entity.getUpdateTime());
        activityDB.setUpdater(entity.getUpdater());

        return activityDB;
    }

    public static ActivityEntity toEntity(ActivityDB activityDB) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(activityDB.getId());
        activityEntity.setSurface(activityDB.getSurface());
        activityEntity.setActivityName(activityDB.getActivityName());
        activityEntity.setActivityTime(new ActivityTime(activityDB.getStartTime(), activityDB.getEndTime()));
        activityEntity.setDescribe(activityDB.getDescribe());
        activityEntity.setCreateTime(activityDB.getCreateTime());
        activityEntity.setCreator(activityDB.getCreator());
        activityEntity.setUpdateTime(activityDB.getUpdateTime());
        activityEntity.setUpdater(activityDB.getUpdater());
        return activityEntity;
    }
}
