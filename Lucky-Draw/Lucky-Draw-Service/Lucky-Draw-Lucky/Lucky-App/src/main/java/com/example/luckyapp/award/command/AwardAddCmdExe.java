package com.example.luckyapp.award.command;

import com.example.luckyapp.assembler.AwardAssembler;
import com.example.luckyclient.dto.cmd.AwardAddCmd;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckydomain.award.AwardEntity;
import com.example.luckydomain.gateway.AwardGateway;
import com.example.luckydomain.gateway.PrizeGateway;
import com.example.luckydomain.prize.PrizeEntity;
import com.example.luckydrawconfig.exception.Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@AllArgsConstructor
public class AwardAddCmdExe {

    private final AwardGateway awardGateway;

    private final PrizeGateway prizeGateway;

    public AwardVO execute(AwardAddCmd cmd) {
       if(Objects.isNull(cmd.getActivityId()))
        {
            throw  new Exception("奖项Id不为空！");
        }

        PrizeEntity one = prizeGateway.one(cmd.getPrizeId());
        // 保存奖项
        AwardEntity entity = awardGateway.save(AwardAssembler.toAddEntity(cmd));
        entity.setPrizeName(one.getPrizeName());

        // 扣取奖品库存
        if (Boolean.FALSE.equals(entity.isPrize())) {
            // 代表该奖项是谢谢参与一类，不需要扣减奖品库存
            return AwardAssembler.toAwardVO(entity);
        }

        int update = prizeGateway.deductionInventory(cmd.getPrizeId(), cmd.getNumber());
        if(update < 1)
       {
        throw new Exception(String.format("扣取奖品：%s, 出错，库存不足或奖品不存在！", cmd.getPrizeId()));
       }

        return AwardAssembler.toAwardVO(entity);
    }
}
