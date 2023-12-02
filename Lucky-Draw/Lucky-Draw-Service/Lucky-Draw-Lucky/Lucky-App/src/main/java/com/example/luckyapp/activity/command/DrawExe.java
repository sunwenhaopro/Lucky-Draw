package com.example.luckyapp.activity.command;

import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckyclient.dto.data.*;
import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.activity.ActivityStatusEnum;
import com.example.luckydomain.activity.ActivityTime;
import com.example.luckydrawconfig.exception.Exception;
import com.example.luckydrawconfig.util.AssertUtil;
import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Slf4j
public class DrawExe {
    private final RedisDeductionAwardNumberDrawExe redisDeductionAwardNumberDrawExe;
    private final RedisRuleQueryExe redisRuleQueryExe;

    @Transactional
    public DrawResultVO execute(ActivityConfigVO activityConfigVO) {
        String key = SecurityUtil.getUserId() + ":" + activityConfigVO.getActivityVO().getId();
        checkActivityTime(activityConfigVO.getActivityVO());
        checkActivityRule(activityConfigVO);
        List<AwardVO> awardList = activityConfigVO.getAwardVOList().stream().filter(item -> item.getNumber() > 0 || "0".equals(item.getPrizeId().toString())).collect(Collectors.toList());
        List<WeightRandom.WeightObj<AwardVO>> weightObjList = new ArrayList<>();
        awardList.forEach(item -> weightObjList.add(new WeightRandom.WeightObj<>(item, item.getProbability())));
        WeightRandom<AwardVO> wr = RandomUtil.weightRandom(weightObjList);
        AwardVO awardVO = wr.next();
        RecordAddCmd recordAddCmd = new RecordAddCmd();
        recordAddCmd.setUserId(SecurityUtil.getUserId());
        recordAddCmd.setActivityId(activityConfigVO.getActivityVO().getId());
        recordAddCmd.setActivityName(activityConfigVO.getActivityVO().getActivityName());
        try {
            recordAddCmd.setAwardId(awardVO.getId());
            recordAddCmd.setIsWinning("0".equals(awardVO.getPrizeId().toString()) ? 0 : 1);
            // 参与奖的话直接设置为4表示已经确认
            recordAddCmd.setState("0".equals(awardVO.getPrizeId().toString()) ? 4 : 1);
            Integer result= redisDeductionAwardNumberDrawExe.deductionAwardNumber(activityConfigVO, awardVO);
            DrawResultVO resultVO = new DrawResultVO();
            resultVO.setId(SecurityUtil.getUserId());
            resultVO.setAwardName(awardVO.getAwardName());
            resultVO.setPrizeName(awardVO.getPrizeName());
            resultVO.setIsDraw("0".equals(awardVO.getPrizeId().toString()) ? Boolean.FALSE : Boolean.TRUE);
            resultVO.setActivityName(activityConfigVO.getActivityVO().getActivityName());
            redisRuleQueryExe.addQuery(key, recordAddCmd,resultVO);
            return resultVO;
        } catch (java.lang.Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            String key2 = "sun" + ":" + activityConfigVO.getActivityVO().getId() + ":" + awardVO.getId();
            redisDeductionAwardNumberDrawExe.invokeStockRollbackLua(key2);
            redisRuleQueryExe.deleteQuery(key);
            throw new Exception("服务繁忙！稍后重试");
        }
    }

    private void checkActivityRule(ActivityConfigVO activityConfigVO) {
        String key = SecurityUtil.getUserId() + ":" + activityConfigVO.getActivityVO().getId();

        RuleVO ruleVO = activityConfigVO.getRuleVO();

        List<Object> queryNumber = redisRuleQueryExe.getQuery(key);
        // 校验最大参与次数
        AssertUtil.isTrue(queryNumber.size() >= ruleVO.getMaxJoinNumber(), "你已达到活动最大参与次数，不可参与！");

        List<Object> winningNumber= queryNumber.stream().filter(item-> "1".equals(item.toString())).collect(Collectors.toList());
        // 校验最大中奖次数
        AssertUtil.isTrue(  winningNumber.size() >= ruleVO.getMaxWinningNumber(), "你已达到最大中奖次数，不可参与！");
    }




    private void checkActivityTime(ActivityVO activityVO) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityTime(new ActivityTime(activityVO.getStartTime(), activityVO.getEndTime()));
        ActivityStatusEnum activityStatus = activityEntity.getActivityTime().getStatus();
        if (!ActivityStatusEnum.START.equals(activityStatus)) {
            throw new Exception(String.format("活动%s", activityStatus.getDescription()));
        }

    }
}
