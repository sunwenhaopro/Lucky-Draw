package com.example.luckyapp.acceptprize.query;


import com.example.luckyapp.assembler.AcceptPrizeAssembler;
import com.example.luckyclient.dto.cmd.AcceptPrizeVO;
import com.example.luckydomain.acceptprize.AcceptPrizeEntity;
import com.example.luckydomain.gateway.AcceptPrizeGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@AllArgsConstructor
public class AcceptPrizeOneExe {

    private final AcceptPrizeGateway acceptPrizeGateway;


    public AcceptPrizeVO execute(Long recordId) {
        AcceptPrizeEntity entity = acceptPrizeGateway.one(recordId);
        return AcceptPrizeAssembler.toAcceptPrizeVO(entity);
    }
}
