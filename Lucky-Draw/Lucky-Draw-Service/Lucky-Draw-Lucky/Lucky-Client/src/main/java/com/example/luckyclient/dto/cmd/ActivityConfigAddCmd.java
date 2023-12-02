package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import java.util.List;


@Data
public class ActivityConfigAddCmd extends Command {

    private ActivityAddCmd activityAddCmd;
    private RuleAddCmd ruleAddCmd;
    private List<AwardAddCmd> awardAddCmdList;
    private List<PrizeAddCmd> prizeAddCmdList;
}
