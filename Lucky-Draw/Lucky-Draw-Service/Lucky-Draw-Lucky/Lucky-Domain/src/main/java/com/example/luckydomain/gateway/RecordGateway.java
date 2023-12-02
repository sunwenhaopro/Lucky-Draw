package com.example.luckydomain.gateway;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.query.RecordListByParamQuery;
import com.example.luckyclient.dto.query.RecordWarnQuery;
import com.example.luckydomain.record.RecordEntity;

import java.math.BigDecimal;
import java.util.List;


public interface RecordGateway {

    RecordEntity save(RecordEntity entity);

    IPage<RecordEntity> page(RecordListByParamQuery query);

    Boolean updateStatus(Long id, Integer status);

    BigDecimal getPrizeMoneyByRecordId(Long recordId);

    List<RecordWarnQuery> findAllDdl();
}
