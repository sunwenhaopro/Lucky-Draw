package com.example.luckyapp.assembler;

import com.example.luckyclient.dto.cmd.AwardAddCmd;
import com.example.luckyclient.dto.cmd.AwardUpdateCmd;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckydomain.award.AwardEntity;
import com.example.luckydomain.award.AwardNumber;
import com.example.luckydrawconfig.util.SecurityUtil;

import java.time.LocalDateTime;


public class AwardAssembler {

    public static AwardEntity toAddEntity(AwardAddCmd cmd) {
        AwardEntity awardEntity = new AwardEntity();
        awardEntity.setPrizeId(cmd.getPrizeId());
        awardEntity.setActivityId(cmd.getActivityId());
        awardEntity.setNumber(new AwardNumber(cmd.getNumber()));
        awardEntity.setAwardName(cmd.getAwardName());
        awardEntity.setAwardPhoto(cmd.getAwardPhoto());
        awardEntity.setProbability(cmd.getProbability());
        awardEntity.setCreateTime(LocalDateTime.now());
        awardEntity.setCreator(SecurityUtil.getUsername());
        awardEntity.setUpdateTime(LocalDateTime.now());
        awardEntity.setUpdater(SecurityUtil.getUsername());

        return awardEntity;
    }

    public static AwardVO toAwardVO(AwardEntity entity) {
        AwardVO awardVO = new AwardVO();
        awardVO.setId(entity.getId());
        awardVO.setActivityId(entity.getActivityId());
        awardVO.setPrizeId(entity.getPrizeId());
        awardVO.setNumber(entity.getNumber().getNumber());
        awardVO.setAwardName(entity.getAwardName());
        awardVO.setAwardPhoto(entity.getAwardPhoto());
        awardVO.setProbability(entity.getProbability());
        awardVO.setCreateTime(entity.getCreateTime());
        awardVO.setCreator(entity.getCreator());
        awardVO.setUpdateTime(entity.getUpdateTime());
        awardVO.setUpdater(entity.getUpdater());
        awardVO.setPrizeName(entity.getPrizeName());
        return awardVO;
    }

    public static AwardEntity toUpdateEntity(AwardUpdateCmd cmd) {
        AwardEntity awardEntity = new AwardEntity();
        awardEntity.setId(cmd.getId());
        awardEntity.setPrizeId(cmd.getPrizeId());
        awardEntity.setActivityId(cmd.getActivityId());
        awardEntity.setNumber(new AwardNumber(cmd.getNumber()));
        awardEntity.setAwardName(cmd.getAwardName());
        awardEntity.setAwardPhoto(cmd.getAwardPhoto());
        awardEntity.setProbability(cmd.getProbability());
        awardEntity.setUpdateTime(LocalDateTime.now());
        awardEntity.setUpdater(SecurityUtil.getUsername());

        return awardEntity;
    }

    public static AwardAddCmd toAwardAddCmd(AwardVO awardVO) {
        AwardAddCmd awardAddCmd = new AwardAddCmd();
        awardAddCmd.setPrizeId(awardVO.getPrizeId());
        awardAddCmd.setNumber(awardVO.getNumber());
        awardAddCmd.setAwardName(awardVO.getAwardName());
        awardAddCmd.setAwardPhoto(awardVO.getAwardPhoto());
        awardAddCmd.setProbability(awardVO.getProbability());
        return awardAddCmd;
    }

    public static AwardEntity toAwardEntity(AwardVO awardVO) {
        AwardEntity awardEntity = new AwardEntity();
        awardEntity.setId(awardVO.getId());
        awardEntity.setPrizeId(awardVO.getPrizeId());
        awardEntity.setActivityId(awardVO.getActivityId());
        awardEntity.setNumber(new AwardNumber(awardVO.getNumber()));
        awardEntity.setAwardPhoto(awardVO.getAwardPhoto());
        awardEntity.setAwardName(awardVO.getAwardName());
        awardEntity.setProbability(awardVO.getProbability());
        awardEntity.setCreateTime(awardVO.getCreateTime());
        awardEntity.setCreator(awardVO.getCreator());
        awardEntity.setUpdateTime(awardVO.getUpdateTime());
        awardEntity.setUpdater(awardVO.getUpdater());

        return awardEntity;
    }
}
