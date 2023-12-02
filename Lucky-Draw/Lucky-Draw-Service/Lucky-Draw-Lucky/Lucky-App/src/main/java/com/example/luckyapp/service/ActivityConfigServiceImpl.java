package com.example.luckyapp.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyapp.activity.command.ActivityAddCmdExe;
import com.example.luckyapp.activity.command.ActivityUpdateCmdExe;
import com.example.luckyapp.activity.query.ActivityListByParamQueryExe;
import com.example.luckyapp.activityrule.command.ActivityRuleAddCmdExe;
import com.example.luckyapp.activityrule.command.ActivityRuleDeleteExe;
import com.example.luckyapp.activityrule.query.ActivityRuleListByParamQueryExe;
import com.example.luckyapp.assembler.ActivityAssembler;
import com.example.luckyapp.assembler.AwardAssembler;
import com.example.luckyapp.award.command.AwardAddCmdExe;
import com.example.luckyapp.award.command.AwardUpdateCmdExe;
import com.example.luckyapp.award.query.AwardListByParamQueryExe;
import com.example.luckyapp.listener.ActivityCreatEvent;

import com.example.luckyapp.prize.command.PrizeAddCmdExe;
import com.example.luckyapp.rule.command.RuleAddCmdExe;
import com.example.luckyapp.rule.command.RuleUpdateCmdExe;
import com.example.luckyapp.rule.query.RuleByIdQuery;
import com.example.luckyclient.api.IActivityConfigService;
import com.example.luckyclient.dto.cmd.*;
import com.example.luckyclient.dto.data.*;
import com.example.luckyclient.dto.query.*;
import com.example.luckydrawconfig.util.AssertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class ActivityConfigServiceImpl implements IActivityConfigService {

    private final ActivityAddCmdExe activityAddCmdExe;
    private final ActivityRuleAddCmdExe activityRuleAddCmdExe;
    private final AwardAddCmdExe awardAddCmdExe;
    private final PrizeAddCmdExe prizeAddCmdExe;
    private final ActivityUpdateCmdExe activityUpdateCmdExe;
    private final ActivityRuleDeleteExe activityRuleDeleteExe;
    private final AwardUpdateCmdExe awardUpdateCmdExe;
    private final RuleUpdateCmdExe ruleUpdateCmdExe;
    private final ActivityListByParamQueryExe activityListByParamQueryExe;
    private final RuleByIdQuery ruleByIdQuery;
    private final ActivityRuleListByParamQueryExe activityRuleListByParamQueryExe;
    private final AwardListByParamQueryExe awardListByParamQueryExe;
    private final RuleAddCmdExe ruleAddCmdExe;
    private final ApplicationEventMulticaster applicationEventMulticaster;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActivityConfigVO add(ActivityConfigAddCmd cmd) {
        RuleVO ruleVO1= ruleAddCmdExe.execute(cmd.getRuleAddCmd());
        ActivityVO activityVO = activityAddCmdExe.execute(cmd.getActivityAddCmd());
        RuleVO ruleVO2 = addActivityRule(activityVO, ruleVO1.getId());
        List<AwardVO> awardVOList = addAward(activityVO, cmd.getAwardAddCmdList(),cmd.getPrizeAddCmdList());
        ActivityConfigVO activityConfigVO = new ActivityConfigVO();
        activityConfigVO.setActivityVO(activityVO);
        activityConfigVO.setRuleVO(ruleVO2);
        activityConfigVO.setAwardVOList(awardVOList);
        applicationEventMulticaster.multicastEvent(new ActivityCreatEvent("",activityConfigVO));
        return activityConfigVO;
    }

    private List<AwardVO> addAward(ActivityVO activityVO, List<AwardAddCmd> awardAddCmdList,List<PrizeAddCmd> prizeAddCmdList) {
        AssertUtil.isTrue(CollectionUtil.isEmpty(awardAddCmdList), "奖项不为空！");
        List<AwardVO> result = new ArrayList<>();
        for(int i=0;i<prizeAddCmdList.size();i++)
        {
            PrizeAddCmd prizeAddCmd=prizeAddCmdList.get(i);
            prizeAddCmd.setInventory(awardAddCmdList.get(i).getNumber());
            PrizeVO prizeVO=  prizeAddCmdExe.execute(prizeAddCmd);
            awardAddCmdList.get(i).setActivityId(activityVO.getId());
            awardAddCmdList.get(i).setPrizeId(prizeVO.getId());
            result.add(awardAddCmdExe.execute(awardAddCmdList.get(i)));
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActivityConfigVO update(ActivityConfigUpdateCmd cmd) {
        ActivityVO activityVO = activityUpdateCmdExe.execute(cmd.getActivityUpdateCmd());
        RuleVO ruleVO1= ruleUpdateCmdExe.execute(cmd.getRuleUpdateCmd());
        activityRuleDeleteExe.execute(activityVO.getId());
        RuleVO ruleVO2 = addActivityRule(activityVO, ruleVO1.getId());
        List<AwardVO> awardVOList = updateAward(activityVO, cmd.getAwardUpdateCmdList());
        ActivityConfigVO activityConfigVO = new ActivityConfigVO();
        activityConfigVO.setActivityVO(activityVO);
        activityConfigVO.setRuleVO(ruleVO2);
        activityConfigVO.setAwardVOList(awardVOList);
        return activityConfigVO;
    }

    private List<AwardVO> updateAward(ActivityVO activityVO, List<AwardUpdateCmd> awardUpdateCmdList) {
        AssertUtil.isTrue(CollectionUtil.isEmpty(awardUpdateCmdList), "奖项不为空！");
        List<AwardVO> result = new ArrayList<>();
        for (AwardUpdateCmd awardUpdateCmd : awardUpdateCmdList) {
            result.add(awardUpdateCmdExe.execute(awardUpdateCmd));
        }
        return result;
    }


    @Override
    public ActivityConfigVO one(Long id) {
        final var activityListByParamQuery = new ActivityListByParamQuery();
        activityListByParamQuery.setId(id);
        List<ActivityVO> activityVOList = activityListByParamQueryExe.execute(activityListByParamQuery).getRecords();
        AssertUtil.isTrue(CollectionUtil.isEmpty(activityVOList), "数据不存在！");
        ActivityVO activityVO = activityVOList.get(0);
        final var activityRuleListByParamQuery = new ActivityRuleListByParamQuery();
        activityRuleListByParamQuery.setActivityId(activityVO.getId());
        ActivityRuleVO activityRuleVO = activityRuleListByParamQueryExe.execute(activityRuleListByParamQuery).get(0);
        RuleVO ruleVO = getRuleVOList(activityRuleVO.getRuleId());
        AwardListByParamQuery awardListByParamQuery = new AwardListByParamQuery();
        awardListByParamQuery.setActivityId(activityVO.getId());
        awardListByParamQuery.setPageSize(1000);
        List<AwardVO> awardVOList = awardListByParamQueryExe.execute(awardListByParamQuery).getRecords();
        ActivityConfigVO activityConfigVO = new ActivityConfigVO();
        activityConfigVO.setActivityVO(activityVO);
        activityConfigVO.setRuleVO(ruleVO);
        activityConfigVO.setAwardVOList(awardVOList);
        return activityConfigVO;
    }

    @Override
    public ActivityConfigCopyVO copy(Long id) {
        ActivityConfigCopyVO activityConfigCopyVO = new ActivityConfigCopyVO();
        ActivityConfigVO activityConfigVO = one(id);
        activityConfigCopyVO.setActivityAddCmd(ActivityAssembler.toActivityAddCmd( activityConfigVO.getActivityVO()));
        activityConfigCopyVO.setRuleAddCmd(activityConfigCopyVO.getRuleAddCmd());
        activityConfigCopyVO.setAwardAddCmdList(
                new Page<AwardVO>().setRecords(activityConfigVO.getAwardVOList()).convert(AwardAssembler::toAwardAddCmd).getRecords()
        );
        return activityConfigCopyVO;
    }


    private RuleVO addActivityRule(ActivityVO activityVO, Long ruleId) {
            ActivityRuleAddCmd activityRuleAddCmd = new ActivityRuleAddCmd();
            activityRuleAddCmd.setActivityId(activityVO.getId());
            activityRuleAddCmd.setRuleId(ruleId);
            ActivityRuleVO activityRuleVOList = activityRuleAddCmdExe.execute(activityRuleAddCmd);
            return getRuleVOList(ruleId);
    }


    private RuleVO getRuleVOList(Long id){
        RuleListByParamQuery query = new RuleListByParamQuery();
        query.setId(id);
        return ruleByIdQuery.execute(id);
    }
}
