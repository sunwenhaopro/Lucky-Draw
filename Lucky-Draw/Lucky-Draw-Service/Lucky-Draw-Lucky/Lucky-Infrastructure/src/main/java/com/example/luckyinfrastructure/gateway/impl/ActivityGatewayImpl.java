package com.example.luckyinfrastructure.gateway.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.luckyclient.dto.query.ActivityListByParamQuery;
import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.gateway.ActivityGateway;
import com.example.luckydrawconfig.exception.Exception;
import com.example.luckyinfrastructure.convertor.ActivityConvertor;
import com.example.luckyinfrastructure.dataobject.ActivityDB;
import com.example.luckyinfrastructure.mapper.ActivityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;


@Slf4j
@Component
public class ActivityGatewayImpl implements ActivityGateway {
    @Resource
    private  ActivityMapper activityMapper;

    @Override
    public ActivityEntity save(ActivityEntity entity) {
        if (Objects.isNull(entity.getId())) {
            return addActivity(entity);
        }
        return updateActivity(entity);
    }

    private ActivityEntity updateActivity(ActivityEntity entity) {
        ActivityDB activityDB = ActivityConvertor.toActivityDB(entity);
       if(activityMapper.updateById(activityDB) <= 0)
       {
         throw new Exception("更新失败") ;
       }
        return ActivityConvertor.toEntity(activityDB);
    }

    private ActivityEntity addActivity(ActivityEntity entity) {
        ActivityDB activityDB = ActivityConvertor.toActivityDB(entity);
       if(activityMapper.insert(activityDB) <= 0){
           throw new Exception("添加失败");
       }
        return ActivityConvertor.toEntity(activityDB);
    }

    @Override
    public IPage<ActivityEntity> page(ActivityListByParamQuery query) {
        IPage<ActivityDB> page = activityMapper.page(new Page<ActivityDB>(query.getPageIndex(), query.getPageSize()), query);
        return page.convert(ActivityConvertor::toEntity);
    }
}
