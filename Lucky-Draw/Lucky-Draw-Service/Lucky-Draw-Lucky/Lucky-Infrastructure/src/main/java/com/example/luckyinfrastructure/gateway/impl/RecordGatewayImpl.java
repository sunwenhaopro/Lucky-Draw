package com.example.luckyinfrastructure.gateway.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.RecordListByParamQuery;
import com.example.luckyclient.dto.query.RecordWarnQuery;
import com.example.luckydomain.gateway.RecordGateway;
import com.example.luckydomain.record.RecordEntity;
import com.example.luckydrawconfig.util.AssertUtil;
import com.example.luckydrawconfig.vo.ExceptionEnum;
import com.example.luckyinfrastructure.convertor.RecordConvertor;
import com.example.luckyinfrastructure.dataobject.RecordDB;
import com.example.luckyinfrastructure.mapper.RecordMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Component
@AllArgsConstructor
public class RecordGatewayImpl implements RecordGateway {

    private final RecordMapper recordMapper;

    @Override
    public RecordEntity save(RecordEntity entity) {
        RecordDB recordDB = RecordConvertor.toRecordDB(entity);
        AssertUtil.isTrue(recordMapper.insert(recordDB) != 1, ExceptionEnum.ADD_ERROR.getDescription());

        return RecordConvertor.toEntity(recordDB);
    }

    @Override
    public IPage<RecordEntity> page(RecordListByParamQuery query) {
        IPage<RecordDB> page = recordMapper.page(new Page<RecordDB>(query.getPageIndex(), query.getPageSize()), query);

        return page.convert(RecordConvertor::toEntity);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
         return recordMapper.updateStatus(id, status) == 1;
    }


    @Override
    public BigDecimal getPrizeMoneyByRecordId(Long recordId) {
        return recordMapper.getPrizeMoneyByRecordId(recordId);
    }

    @Override
    public List<RecordWarnQuery> findAllDdl() {
        return    recordMapper.findAllDdl();
    }
}
