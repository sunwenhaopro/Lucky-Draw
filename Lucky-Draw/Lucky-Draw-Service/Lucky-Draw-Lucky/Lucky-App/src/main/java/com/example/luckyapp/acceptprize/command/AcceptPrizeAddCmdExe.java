package com.example.luckyapp.acceptprize.command;


import com.example.luckyapp.assembler.AcceptPrizeAssembler;
import com.example.luckyclient.dto.cmd.AcceptPrizeVO;
import com.example.luckyclient.dto.query.AcceptPrizeAddCmd;
import com.example.luckydomain.acceptprize.AcceptPrizeEntity;
import com.example.luckydomain.gateway.AcceptPrizeGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@AllArgsConstructor
public class AcceptPrizeAddCmdExe {

    private final AcceptPrizeGateway acceptPrizeGateway;

    public AcceptPrizeVO execute(AcceptPrizeAddCmd cmd) {
        AcceptPrizeEntity entity = acceptPrizeGateway.save(AcceptPrizeAssembler.toEntity(cmd));

        return AcceptPrizeAssembler.toAcceptPrizeVO(entity);
    }
}
