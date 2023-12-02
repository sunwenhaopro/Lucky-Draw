package com.example.luckyapp.award.command;



import com.example.luckyapp.assembler.AwardAssembler;
import com.example.luckyclient.dto.cmd.AwardUpdateCmd;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckydomain.award.AwardEntity;
import com.example.luckydomain.gateway.AwardGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class AwardUpdateCmdExe {
    private final AwardGateway awardGateway;
    public AwardVO execute(AwardUpdateCmd cmd) {
        AwardEntity entity = awardGateway.save(AwardAssembler.toUpdateEntity(cmd));

        return AwardAssembler.toAwardVO(entity);
    }
}
