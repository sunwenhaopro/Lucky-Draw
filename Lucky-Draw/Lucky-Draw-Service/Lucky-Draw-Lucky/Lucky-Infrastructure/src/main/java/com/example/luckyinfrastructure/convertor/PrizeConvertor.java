package com.example.luckyinfrastructure.convertor;


import com.example.luckydomain.prize.PrizeEntity;
import com.example.luckydomain.prize.Tnventory;
import com.example.luckyinfrastructure.dataobject.PrizeDB;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class PrizeConvertor {
    public static PrizeDB toPrizeDB(PrizeEntity entity) {
        PrizeDB prizeDB = new PrizeDB();
        prizeDB.setId(entity.getId());
        prizeDB.setPrizeName(entity.getPrizeName());
        prizeDB.setInventory(entity.getInventory().getInventory());
        prizeDB.setMoney(new BigDecimal(entity.getMoney().toString()));
        prizeDB.setType(entity.getType());
        prizeDB.setCreateTime(LocalDateTime.now());
        prizeDB.setCreator(entity.getCreator());
        prizeDB.setUpdateTime(LocalDateTime.now());
        return prizeDB;
    }

    public static PrizeEntity toEntity(PrizeDB prizeDB) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(prizeDB.getId());
        prizeEntity.setPrizeName(prizeDB.getPrizeName());
        prizeEntity.setInventory(new Tnventory(prizeDB.getInventory()));
        prizeEntity.setMoney(new BigDecimal(prizeDB.getMoney().toString()));
        prizeEntity.setType(prizeDB.getType());
        prizeEntity.setCreateTime(prizeDB.getCreateTime());
        prizeEntity.setCreator(prizeDB.getCreator());
        prizeEntity.setUpdateTime(prizeDB.getUpdateTime());

        return prizeEntity;
    }
}
