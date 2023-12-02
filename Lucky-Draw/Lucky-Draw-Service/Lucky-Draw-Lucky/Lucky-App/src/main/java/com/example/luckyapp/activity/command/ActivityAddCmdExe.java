package com.example.luckyapp.activity.command;


import com.example.luckyapp.assembler.ActivityAssembler;
import com.example.luckyclient.dto.cmd.ActivityAddCmd;
import com.example.luckyclient.dto.data.ActivityVO;
import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.gateway.ActivityGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class ActivityAddCmdExe {

    private final ActivityGateway activityGateway;

    public ActivityVO execute(ActivityAddCmd cmd) {
        ActivityEntity entity = activityGateway.save(ActivityAssembler.toAddEntity(cmd));
        return ActivityAssembler.toActivityVO(entity);
    }
}
