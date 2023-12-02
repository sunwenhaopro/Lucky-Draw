package com.example.luckyapp.prize.command;


import com.example.luckyapp.assembler.PrizeAssembler;
import com.example.luckyclient.dto.cmd.PrizeUpdateCmd;
import com.example.luckyclient.dto.data.PrizeVO;
import com.example.luckydomain.gateway.PrizeGateway;
import com.example.luckydomain.prize.PrizeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class PrizeUpdateCmdExe {

    private final PrizeGateway prizeGateway;

    public PrizeVO execute(PrizeUpdateCmd cmd) {
        PrizeEntity prizeEntity = prizeGateway.save(PrizeAssembler.toUpdateEntity(cmd));

        return PrizeAssembler.toPrizeVO(prizeEntity);
    }
}
