package com.example.luckyinfrastructure.convertor;


import com.example.luckydomain.acceptprize.AcceptPrizeEntity;
import com.example.luckyinfrastructure.dataobject.AcceptPrizeDB;

public class AcceptPrizeConvertor {
    public static AcceptPrizeDB toAcceptPrizeDB(AcceptPrizeEntity toEntity) {
        AcceptPrizeDB acceptPrizeDB = new AcceptPrizeDB();
        acceptPrizeDB.setRecordId(toEntity.getRecordId());
        acceptPrizeDB.setPhone(toEntity.getPhone());
        acceptPrizeDB.setAddress(toEntity.getAddress());
        acceptPrizeDB.setCreateTime(toEntity.getCreateTime());
        acceptPrizeDB.setCreator(toEntity.getCreator());
        acceptPrizeDB.setUpdateTime(toEntity.getUpdateTime());
        acceptPrizeDB.setUpdater(toEntity.getUpdater());

        return acceptPrizeDB;
    }

    public static AcceptPrizeEntity toEntity(AcceptPrizeDB acceptPrizeDB) {
        AcceptPrizeEntity entity = new AcceptPrizeEntity();
        entity.setId(acceptPrizeDB.getId());
        entity.setRecordId(acceptPrizeDB.getRecordId());
        entity.setPhone(acceptPrizeDB.getPhone());
        entity.setAddress(acceptPrizeDB.getAddress());
        entity.setCreateTime(acceptPrizeDB.getCreateTime());
        entity.setCreator(acceptPrizeDB.getCreator());
        entity.setUpdateTime(acceptPrizeDB.getUpdateTime());
        entity.setUpdater(acceptPrizeDB.getUpdater());

        return entity;
    }
}
