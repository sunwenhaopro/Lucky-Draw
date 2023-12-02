package com.example.luckyinfrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.PrizeListByParamQuery;
import com.example.luckyinfrastructure.dataobject.PrizeDB;
import org.apache.ibatis.annotations.Param;

public interface PrizeMapper extends BaseMapper<PrizeDB> {

    IPage<PrizeDB> page(@Param("page") Page<PrizeDB> prizeDBPage, @Param("query") PrizeListByParamQuery query);

    int deductionInventory(@Param("prizeId") Long prizeId, @Param("number") Integer number);
}
