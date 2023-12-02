package com.example.luckyinfrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.RecordListByParamQuery;
import com.example.luckyclient.dto.query.RecordWarnQuery;
import com.example.luckyinfrastructure.dataobject.RecordDB;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


public interface RecordMapper extends BaseMapper<RecordDB> {

    IPage<RecordDB> page(@Param("recordDBPage") Page<RecordDB> recordDBPage, @Param("query") RecordListByParamQuery query);


    BigDecimal getPrizeMoneyByRecordId(@Param("recordId") Long recordId);

    Integer updateStatus(@Param("id") Long id, @Param("status") Integer status);

    List<RecordWarnQuery> findAllDdl();
}




