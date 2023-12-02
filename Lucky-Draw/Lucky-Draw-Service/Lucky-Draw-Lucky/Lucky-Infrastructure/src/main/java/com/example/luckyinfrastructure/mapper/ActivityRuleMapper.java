package com.example.luckyinfrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.luckyclient.dto.query.ActivityRuleListByParamQuery;
import com.example.luckyinfrastructure.dataobject.ActivityRuleDB;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ActivityRuleMapper extends BaseMapper<ActivityRuleDB> {

    List<ActivityRuleDB> list(@Param("query") ActivityRuleListByParamQuery query);

    void deleteByActivityId(@Param("activityId") Long activityId);
}




