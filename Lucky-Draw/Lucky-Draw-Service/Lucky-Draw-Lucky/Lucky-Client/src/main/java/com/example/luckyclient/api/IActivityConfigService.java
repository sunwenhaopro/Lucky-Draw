package com.example.luckyclient.api;


import com.example.luckyclient.dto.cmd.ActivityConfigAddCmd;
import com.example.luckyclient.dto.cmd.ActivityConfigUpdateCmd;
import com.example.luckyclient.dto.data.ActivityConfigCopyVO;
import com.example.luckyclient.dto.data.ActivityConfigVO;

public interface IActivityConfigService {

    ActivityConfigVO add(ActivityConfigAddCmd cmd);

    ActivityConfigVO update(ActivityConfigUpdateCmd cmd);

    ActivityConfigVO one(Long id);

    ActivityConfigCopyVO copy(Long id);
}
