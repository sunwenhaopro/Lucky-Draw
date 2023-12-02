package com.example.luckyinfrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.AwardListByParamQuery;
import com.example.luckyinfrastructure.dataobject.AwardDB;
import org.apache.ibatis.annotations.Param;


public interface AwardMapper extends BaseMapper<AwardDB> {

    IPage<AwardDB> page(@Param("awardDBPage") Page<AwardDB> awardDBPage, @Param("query") AwardListByParamQuery query);

    int deductionAwardNumber(@Param("awardId") Long awardId);
}




