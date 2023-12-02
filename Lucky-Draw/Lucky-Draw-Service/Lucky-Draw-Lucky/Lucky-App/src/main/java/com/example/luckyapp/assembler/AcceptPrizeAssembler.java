package com.example.luckyapp.assembler;

import com.example.luckyclient.dto.cmd.AcceptPrizeVO;
import com.example.luckyclient.dto.query.AcceptPrizeAddCmd;
import com.example.luckydomain.acceptprize.AcceptPrizeEntity;
import com.example.luckydrawconfig.util.SecurityUtil;

import java.time.LocalDateTime;


public class AcceptPrizeAssembler {

    public static AcceptPrizeEntity toEntity(AcceptPrizeAddCmd cmd) {
        AcceptPrizeEntity entity = new AcceptPrizeEntity();
        entity.setRecordId(cmd.getRecordId());
        entity.setPhone(cmd.getPhone());
        entity.setAddress(cmd.getAddress());
        entity.setCreateTime(LocalDateTime.now());
        entity.setCreator(SecurityUtil.getUsername());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdater(SecurityUtil.getUsername());
        return entity;
    }

    public static AcceptPrizeVO toAcceptPrizeVO(AcceptPrizeEntity entity) {
        AcceptPrizeVO acceptPrizeVO = new AcceptPrizeVO();
        acceptPrizeVO.setId(entity.getId());
        acceptPrizeVO.setPhone(entity.getPhone());
        acceptPrizeVO.setAddress(entity.getAddress());
        acceptPrizeVO.setCreateTime(entity.getCreateTime());
        return acceptPrizeVO;
    }
}
