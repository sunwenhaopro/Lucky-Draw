package com.example.luckyinfrastructure.gateway.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.AwardListByParamQuery;
import com.example.luckydomain.award.AwardEntity;
import com.example.luckydomain.gateway.AwardGateway;
import com.example.luckydrawconfig.exception.Exception;
import com.example.luckyinfrastructure.convertor.AwardConvertor;
import com.example.luckyinfrastructure.dataobject.AwardDB;
import com.example.luckyinfrastructure.mapper.AwardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@AllArgsConstructor
public class AwardGatewayImpl implements AwardGateway {

    private final AwardMapper awardMapper;

    @Override
    public AwardEntity save(AwardEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addAward(entity);
        }
        return updateAward(entity);
    }

    private AwardEntity addAward(AwardEntity entity) {
        AwardDB awardDB = AwardConvertor.toAwardDB(entity);


        int insert=awardMapper.insert(awardDB);
        if(insert<=0)
        {
            throw new Exception("添加失败");
        }
        return AwardConvertor.toEntity(awardDB);
    }

    private AwardEntity updateAward(AwardEntity entity) {
        AwardDB awardDB = AwardConvertor.toAwardDB(entity);

        int insert=awardMapper.updateById(awardDB);
        if(insert<=0)
        {
            throw new Exception("更新失败");
        }

        return AwardConvertor.toEntity(awardDB);
    }

    @Override
    public IPage<AwardEntity> page(AwardListByParamQuery query) {
        IPage<AwardDB> page = awardMapper.page(new Page<AwardDB>(query.getPageIndex(), query.getPageSize()), query);

        return page.convert(AwardConvertor::toEntity);
    }

    @Override
    public int deductionAwardNumber(Long awardId) {
        return
        awardMapper.deductionAwardNumber(awardId);
    }
}
