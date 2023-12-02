package com.example.luckyclient.dto.data;


import com.example.luckyclient.dto.cmd.ActivityAddCmd;
import com.example.luckyclient.dto.cmd.AwardAddCmd;
import com.example.luckyclient.dto.cmd.RuleAddCmd;
import lombok.Data;

import java.util.List;


@Data
public class ActivityConfigCopyVO {
    private ActivityAddCmd activityAddCmd;

    private RuleAddCmd ruleAddCmd;

    private List<AwardAddCmd> awardAddCmdList;
}
