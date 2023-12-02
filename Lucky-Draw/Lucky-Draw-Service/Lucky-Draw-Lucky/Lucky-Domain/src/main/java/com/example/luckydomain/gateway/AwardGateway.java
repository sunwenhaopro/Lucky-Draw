package com.example.luckydomain.gateway;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.query.AwardListByParamQuery;
import com.example.luckydomain.award.AwardEntity;

public interface AwardGateway {

    AwardEntity save(AwardEntity entity);

    IPage<AwardEntity> page(AwardListByParamQuery query);

    int deductionAwardNumber(Long awardId);
}
