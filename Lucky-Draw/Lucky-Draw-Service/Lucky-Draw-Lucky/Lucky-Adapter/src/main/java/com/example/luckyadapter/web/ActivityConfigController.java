package com.example.luckyadapter.web;

import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IActivityConfigService;
import com.example.luckyclient.dto.cmd.ActivityConfigAddCmd;
import com.example.luckyclient.dto.data.ActivityConfigCopyVO;
import com.example.luckyclient.dto.data.ActivityConfigVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/activityConfig")
public class ActivityConfigController {

    private final IActivityConfigService activityConfigService;

    @PostMapping("/add")
    public ActivityConfigVO add(@Validated @RequestBody ActivityConfigAddCmd cmd){
        return activityConfigService.add(cmd);
    }

    @GetMapping("/one")
    public ActivityConfigVO one(@RequestParam("id") Long id){
        return activityConfigService.one(id);
    }

    @GetMapping("/copy")
    public ActivityConfigCopyVO copy(@RequestParam("id") Long id){
        return activityConfigService.copy(id);
    }
}
