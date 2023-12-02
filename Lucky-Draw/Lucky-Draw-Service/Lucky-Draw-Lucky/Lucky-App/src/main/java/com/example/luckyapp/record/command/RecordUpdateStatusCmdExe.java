package com.example.luckyapp.record.command;


import com.example.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import com.example.luckydomain.gateway.RecordGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class RecordUpdateStatusCmdExe {

    private final RecordGateway recordGateway;

    public Boolean execute(RecordUpdateStatusCmd cmd) {
        Boolean updateStatus = recordGateway.updateStatus(cmd.getId(), cmd.getState());
        log.info("修改记录失败：记录id：{}，状态值：{}", cmd.getId(), cmd.getState());
        return updateStatus;
    }
}
