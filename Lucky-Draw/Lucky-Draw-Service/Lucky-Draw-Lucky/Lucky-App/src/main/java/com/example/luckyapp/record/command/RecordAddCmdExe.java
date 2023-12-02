package com.example.luckyapp.record.command;

import com.example.luckyapp.assembler.RecordAssembler;
import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckyclient.dto.data.RecordVO;
import com.example.luckydomain.gateway.RecordGateway;
import com.example.luckydomain.record.RecordEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class RecordAddCmdExe {

    private final RecordGateway recordGateway;

    public RecordVO execute(RecordAddCmd cmd) {
        RecordEntity entity = recordGateway.save(RecordAssembler.toAddEntity(cmd));
        return RecordAssembler.toRecordVO(entity);
    }
}
