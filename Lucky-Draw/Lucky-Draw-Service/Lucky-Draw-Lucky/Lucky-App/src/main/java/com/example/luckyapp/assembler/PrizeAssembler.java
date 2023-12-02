package com.example.luckyapp.assembler;


import com.example.luckyclient.dto.cmd.PrizeAddCmd;
import com.example.luckyclient.dto.cmd.PrizeUpdateCmd;
import com.example.luckyclient.dto.data.PrizeVO;
import com.example.luckydomain.prize.PrizeEntity;
import com.example.luckydomain.prize.Tnventory;
import com.example.luckydrawconfig.util.SecurityUtil;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public class PrizeAssembler {
    public static PrizeEntity toAddEntity(PrizeAddCmd cmd) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setPrizeName(cmd.getPrizeName());
        prizeEntity.setInventory(new Tnventory(cmd.getInventory()));
        prizeEntity.setMoney(new BigDecimal('0'));
        prizeEntity.setType(cmd.getType());
        prizeEntity.setCreateTime(LocalDateTime.now());
        prizeEntity.setCreator(SecurityUtil.getUsername());
        prizeEntity.setUpdateTime(LocalDateTime.now());
        return prizeEntity;
    }

    public static PrizeVO toPrizeVO(PrizeEntity prizeEntity) {
        PrizeVO prizeVO = new PrizeVO();
        prizeVO.setId(prizeEntity.getId());
        prizeVO.setPrizeName(prizeEntity.getPrizeName());
        prizeVO.setInventory(prizeEntity.getInventory().getInventory());
        prizeVO.setMoney(new BigDecimal('0'));
        prizeVO.setType(prizeEntity.getType());
        prizeVO.setCreateTime(prizeEntity.getCreateTime());
        prizeVO.setCreator(prizeEntity.getCreator());
        prizeVO.setUpdateTime(prizeEntity.getUpdateTime());
        return prizeVO;
    }

    public static PrizeEntity toUpdateEntity(PrizeUpdateCmd cmd) {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(cmd.getId());
        prizeEntity.setPrizeName(cmd.getPrizeName());
        prizeEntity.setInventory(new Tnventory(cmd.getInventory()));
        prizeEntity.setMoney(new BigDecimal(cmd.getMoney().toString()));
        prizeEntity.setType(cmd.getType());
        prizeEntity.setUpdateTime(LocalDateTime.now());
        return prizeEntity;
    }
}
