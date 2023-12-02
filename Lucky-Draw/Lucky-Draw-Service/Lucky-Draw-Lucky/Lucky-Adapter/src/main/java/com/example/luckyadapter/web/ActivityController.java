package com.example.luckyadapter.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IActivityService;

import com.example.luckyclient.dto.data.ActivityVO;

import com.example.luckyclient.dto.data.DrawResultVO;
import com.example.luckyclient.dto.query.ActivityListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/activity")
public class ActivityController {


    private final IActivityService activityService;

    @PostMapping("/page")
    public IPage<ActivityVO> page(@RequestBody ActivityListByParamQuery query) {
        return activityService.page(query);
    }

    @GetMapping("/one")
    public ActivityVO one(@RequestParam("id") Long id) {
        return activityService.one(id);
    }

    @GetMapping("/draw")
    public DrawResultVO draw(@RequestParam("id") Long activityId)
    {
        return activityService.draw(activityId);
    }

}
