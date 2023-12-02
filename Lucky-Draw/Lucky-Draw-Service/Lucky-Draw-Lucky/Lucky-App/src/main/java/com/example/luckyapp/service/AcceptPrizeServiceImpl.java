package com.example.luckyapp.service;

import com.example.luckyapp.acceptprize.command.AcceptPrizeAddCmdExe;
import com.example.luckyapp.acceptprize.query.AcceptPrizeOneExe;
import com.example.luckyapp.record.command.RecordUpdateStatusCmdExe;
import com.example.luckyclient.api.IAcceptPrizeService;
import com.example.luckyclient.dto.cmd.AcceptPrizeVO;
import com.example.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import com.example.luckyclient.dto.query.AcceptPrizeAddCmd;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@AllArgsConstructor
public class AcceptPrizeServiceImpl implements IAcceptPrizeService {

    private final AcceptPrizeAddCmdExe acceptPrizeAddCmdExe;
    private final RecordUpdateStatusCmdExe recordUpdateStatusCmdExe;
    private final AcceptPrizeOneExe acceptPrizeOneExe;


    @Override
    public AcceptPrizeVO one(Long recordId) {
        return acceptPrizeOneExe.execute(recordId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AcceptPrizeVO add(AcceptPrizeAddCmd cmd) {

        AcceptPrizeVO execute = acceptPrizeAddCmdExe.execute(cmd);

        final var statusCmd = new RecordUpdateStatusCmd();
        statusCmd.setId(cmd.getRecordId());
        statusCmd.setState(2);
        recordUpdateStatusCmdExe.execute(statusCmd);

        return execute;
    }
}
