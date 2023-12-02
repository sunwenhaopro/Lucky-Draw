package com.example.luckyinfrastructure.convertor;

import com.example.luckydomain.record.RecordEntity;
import com.example.luckyinfrastructure.dataobject.RecordDB;

public class RecordConvertor {
    public static RecordDB toRecordDB(RecordEntity entity) {
        RecordDB recordDB = new RecordDB();
        recordDB.setId(entity.getId());
        recordDB.setUserId(entity.getUserId());
        recordDB.setActivityId(entity.getActivityId());
        recordDB.setActivityName(entity.getActivityName());
        recordDB.setAwardId(entity.getAwardId());
        recordDB.setIsWinning(entity.getIsWinning());
        recordDB.setCreateTime(entity.getCreateTime());
        recordDB.setCreator(entity.getCreator());
        recordDB.setUpdateTime(entity.getUpdateTime());
        recordDB.setUpdater(entity.getUpdater());
        recordDB.setState(entity.getState());
        return recordDB;
    }

    public static RecordEntity toEntity(RecordDB recordDB) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setAwardName(recordDB.getAwardName());
        recordEntity.setPrizeName(recordDB.getPrizeName());
        recordEntity.setId(recordDB.getId());
        recordEntity.setUserId(recordDB.getUserId());
        recordEntity.setActivityId(recordDB.getActivityId());
        recordEntity.setActivityName(recordDB.getActivityName());
        recordEntity.setAwardId(recordDB.getAwardId());
        recordEntity.setIsWinning(recordDB.getIsWinning());
        recordEntity.setCreateTime(recordDB.getCreateTime());
        recordEntity.setCreator(recordDB.getCreator());
        recordEntity.setUpdateTime(recordDB.getUpdateTime());
        recordEntity.setUpdater(recordDB.getUpdater());
        recordEntity.setPrizeType(recordDB.getPrizeType());
        recordEntity.setState(recordDB.getState());
        return recordEntity;
    }
}
