package com.example.luckyinfrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.RuleListByParamQuery;
import com.example.luckyinfrastructure.dataobject.RuleDB;
import org.apache.ibatis.annotations.Param;

/**
* @author 21967
* @description 针对表【bld_rule】的数据库操作Mapper
* @createDate 2023-08-15 21:38:15
* @Entity com.example.LuckyDrawUser.entity.Rule
*/
public interface RuleMapper extends BaseMapper<RuleDB> {
    IPage<RuleDB> page(@Param("ruleDBPage") Page<RuleDB> ruleDBPage, @Param("query") RuleListByParamQuery query);

    RuleDB findOneById(@Param("id") Long id);
}




