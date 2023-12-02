package com.example.luckyapp.assembler;


import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckyclient.dto.data.RecordVO;
import com.example.luckydomain.record.RecordEntity;

import java.time.LocalDateTime;


public class RecordAssembler {
    public static RecordEntity toAddEntity(RecordAddCmd cmd) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setUserId(cmd.getUserId());
        recordEntity.setActivityId(cmd.getActivityId());
        recordEntity.setActivityName(cmd.getActivityName());
        recordEntity.setAwardId(cmd.getAwardId());
        recordEntity.setIsWinning(cmd.getIsWinning());
        recordEntity.setState(cmd.getState());
        recordEntity.setCreateTime(LocalDateTime.now());
        recordEntity.setCreator("彩蛋");
        recordEntity.setUpdateTime(LocalDateTime.now());
        recordEntity.setUpdater("彩蛋");
        return recordEntity;
    }

    public static RecordVO toRecordVO(RecordEntity entity) {
        RecordVO recordVO = new RecordVO();
        recordVO.setAwardName(entity.getAwardName());
        recordVO.setPrizeName(entity.getPrizeName());
        recordVO.setId(entity.getId());
        recordVO.setUserId(entity.getUserId());
        recordVO.setActivityId(entity.getActivityId());
        recordVO.setActivityName(entity.getActivityName());
        recordVO.setAwardId(entity.getAwardId());
        recordVO.setIsWinning(entity.getIsWinning());
        recordVO.setState(entity.getState());
        recordVO.setCreateTime(entity.getCreateTime());
        recordVO.setCreator(entity.getCreator());
        recordVO.setUpdateTime(entity.getUpdateTime());
        recordVO.setUpdater(entity.getUpdater());
        recordVO.setPrizeType(entity.getPrizeType());
        return recordVO;
    }
}
