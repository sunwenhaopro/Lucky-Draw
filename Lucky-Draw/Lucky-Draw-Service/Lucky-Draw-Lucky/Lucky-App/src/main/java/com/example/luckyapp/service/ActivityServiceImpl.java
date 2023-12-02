package com.example.luckyapp.service;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.activity.command.ActivityAddCmdExe;
import com.example.luckyapp.activity.command.ActivityUpdateCmdExe;
import com.example.luckyapp.activity.command.DrawExe;
import com.example.luckyapp.activity.query.ActivityListByParamQueryExe;
import com.example.luckyclient.api.IActivityConfigService;
import com.example.luckyclient.api.IActivityService;
import com.example.luckyclient.dto.cmd.ActivityAddCmd;
import com.example.luckyclient.dto.cmd.ActivityUpdateCmd;
import com.example.luckyclient.dto.data.ActivityConfigVO;
import com.example.luckyclient.dto.data.ActivityVO;
import com.example.luckyclient.dto.data.DrawResultVO;
import com.example.luckyclient.dto.query.ActivityListByParamQuery;
import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class ActivityServiceImpl implements IActivityService {

    private final ActivityAddCmdExe activityAddCmdExe;
    private final ActivityUpdateCmdExe activityUpdateCmdExe;
    private final ActivityListByParamQueryExe activityListByParamQueryExe;
    private final IActivityConfigService  activityConfigService;
    private final DrawExe drawExe;

    @Override
    public ActivityVO add(ActivityAddCmd cmd) {
        return activityAddCmdExe.execute(cmd);
    }

    @Override
    public ActivityVO update(ActivityUpdateCmd cmd) {
        return activityUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<ActivityVO> page(ActivityListByParamQuery query) {
        return activityListByParamQueryExe.execute(query);
    }

    @Override
    public ActivityVO one(Long id) {
        final var query = new ActivityListByParamQuery();
        query.setId(id);
        IPage<ActivityVO> page = page(query);

        if (CollectionUtil.isEmpty(page.getRecords())) {
            return null;
        }

        return page.getRecords().get(0);
    }
    @Override
    public DrawResultVO draw(Long activityId) {
        log.info("用户：{}，开始抽奖...", SecurityUtil.getUsername());
        ActivityConfigVO activityConfigVO = activityConfigService.one(activityId);
        return drawExe.execute(activityConfigVO);
    }

}
