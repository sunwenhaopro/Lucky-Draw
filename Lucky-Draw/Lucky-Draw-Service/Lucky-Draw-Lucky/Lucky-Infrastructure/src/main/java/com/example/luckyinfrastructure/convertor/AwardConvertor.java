package com.example.luckyinfrastructure.convertor;


import com.example.luckydomain.award.AwardEntity;
import com.example.luckydomain.award.AwardNumber;
import com.example.luckyinfrastructure.dataobject.AwardDB;

public class AwardConvertor {
    public static AwardDB toAwardDB(AwardEntity entity) {
        AwardDB awardDB = new AwardDB();
        awardDB.setId(entity.getId());
        awardDB.setPrizeId(entity.getPrizeId());
        awardDB.setActivityId(entity.getActivityId());
        awardDB.setNumber(entity.getNumber().getNumber());
        awardDB.setAwardName(entity.getAwardName());
        awardDB.setAwardPhoto(entity.getAwardPhoto());
        awardDB.setProbability(entity.getProbability());
        awardDB.setCreateTime(entity.getCreateTime());
        awardDB.setCreator(entity.getCreator());
        awardDB.setUpdateTime(entity.getUpdateTime());
        awardDB.setUpdater(entity.getUpdater());

        return awardDB;
    }

    public static AwardEntity toEntity(AwardDB awardDB) {
        AwardEntity awardEntity = new AwardEntity();
        awardEntity.setId(awardDB.getId());
        awardEntity.setPrizeId(awardDB.getPrizeId());
        awardEntity.setActivityId(awardDB.getActivityId());
        awardEntity.setNumber(new AwardNumber(awardDB.getNumber()));
        awardEntity.setAwardName(awardDB.getAwardName());
        awardEntity.setAwardPhoto(awardDB.getAwardPhoto());
        awardEntity.setProbability(awardDB.getProbability());
        awardEntity.setCreateTime(awardDB.getCreateTime());
        awardEntity.setCreator(awardDB.getCreator());
        awardEntity.setUpdateTime(awardDB.getUpdateTime());
        awardEntity.setUpdater(awardDB.getUpdater());
        awardEntity.setPrizeName(awardDB.getPrizeName());

        return awardEntity;
    }
}
