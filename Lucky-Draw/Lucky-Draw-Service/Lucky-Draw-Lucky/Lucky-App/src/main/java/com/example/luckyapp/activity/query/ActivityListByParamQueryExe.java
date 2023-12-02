package com.example.luckyapp.activity.query;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.assembler.ActivityAssembler;
import com.example.luckyclient.dto.data.ActivityVO;
import com.example.luckyclient.dto.query.ActivityListByParamQuery;
import com.example.luckydomain.activity.ActivityEntity;
import com.example.luckydomain.gateway.ActivityGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class ActivityListByParamQueryExe {

    private final ActivityGateway activityGateway;

    public IPage<ActivityVO> execute(ActivityListByParamQuery query) {
        IPage<ActivityEntity> page = activityGateway.page(query);

        return page.convert(ActivityAssembler::toActivityVO);
    }
}
