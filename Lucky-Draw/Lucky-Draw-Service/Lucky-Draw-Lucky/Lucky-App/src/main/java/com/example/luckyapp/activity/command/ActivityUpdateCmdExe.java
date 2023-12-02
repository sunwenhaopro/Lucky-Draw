package com.example.luckyapp.activity.command;


import com.example.luckyapp.assembler.ActivityAssembler;
import com.example.luckyclient.dto.cmd.ActivityUpdateCmd;
import com.example.luckyclient.dto.data.ActivityVO;
import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.gateway.ActivityGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class ActivityUpdateCmdExe {

    private final ActivityGateway activityGateway;

    public ActivityVO execute(ActivityUpdateCmd cmd) {
        ActivityEntity entity = activityGateway.save(ActivityAssembler.toUpdateEntity(cmd));

        return ActivityAssembler.toActivityVO(entity);
    }
}
