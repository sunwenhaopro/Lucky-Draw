package com.example.luckyinfrastructure.gateway.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.PrizeListByParamQuery;
import com.example.luckydomain.gateway.PrizeGateway;
import com.example.luckydomain.prize.PrizeEntity;
import com.example.luckydrawconfig.exception.Exception;
import com.example.luckyinfrastructure.convertor.PrizeConvertor;
import com.example.luckyinfrastructure.dataobject.PrizeDB;
import com.example.luckyinfrastructure.mapper.PrizeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@AllArgsConstructor
public class PrizeGatewayImpl implements PrizeGateway {

    /**
     * 构造器注入
     */
    private final PrizeMapper prizeMapper;

    @Override
    public PrizeEntity save(PrizeEntity entity) {
        if (Objects.isNull(entity.getId())){
            return addPrize(entity);
        }
        return updatePrize(entity);
    }

    private PrizeEntity updatePrize(PrizeEntity entity) {
        PrizeDB prizeDB = PrizeConvertor.toPrizeDB(entity);
        int update = prizeMapper.updateById(prizeDB);
        if(update<=0){
            throw new Exception("更新失败");
        }
        return PrizeConvertor.toEntity(prizeDB);
    }

    private PrizeEntity addPrize(PrizeEntity entity) {
        PrizeDB prizeDB = PrizeConvertor.toPrizeDB(entity);
        int insert=prizeMapper.insert(prizeDB);
        if(insert<=0)
        {
            throw  new Exception("添加失败");
        }
        return PrizeConvertor.toEntity(prizeDB);
    }

    @Override
    public IPage<PrizeEntity> page(PrizeListByParamQuery query) {
        IPage<PrizeDB> page = prizeMapper.page(new Page<PrizeDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(PrizeConvertor::toEntity);
    }

    @Override
    public int deductionInventory(Long prizeId, Integer number) {
        return prizeMapper.deductionInventory(prizeId, number);
    }
}
