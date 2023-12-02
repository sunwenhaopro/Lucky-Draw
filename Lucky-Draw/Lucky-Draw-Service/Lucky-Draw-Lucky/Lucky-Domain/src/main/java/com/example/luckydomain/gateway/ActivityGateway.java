package com.example.luckydomain.gateway;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.query.ActivityListByParamQuery;
import com.example.luckydomain.activity.ActivityEntity;


public interface ActivityGateway {

    ActivityEntity save(ActivityEntity entity);


    IPage<ActivityEntity> page(ActivityListByParamQuery query);

}
