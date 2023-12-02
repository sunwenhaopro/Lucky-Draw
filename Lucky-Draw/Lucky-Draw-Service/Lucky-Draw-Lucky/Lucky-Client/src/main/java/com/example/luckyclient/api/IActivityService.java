package com.example.luckyclient.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.cmd.ActivityAddCmd;
import com.example.luckyclient.dto.cmd.ActivityUpdateCmd;
import com.example.luckyclient.dto.data.ActivityVO;
import com.example.luckyclient.dto.data.DrawResultVO;
import com.example.luckyclient.dto.query.ActivityListByParamQuery;


public interface IActivityService {

    ActivityVO add(ActivityAddCmd cmd);

    ActivityVO update(ActivityUpdateCmd cmd);


    IPage<ActivityVO> page(ActivityListByParamQuery query);

    ActivityVO one(Long id);

     DrawResultVO draw(Long activityId);
}
