package com.example.luckyapp.award.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.assembler.AwardAssembler;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckyclient.dto.query.AwardListByParamQuery;
import com.example.luckydomain.award.AwardEntity;
import com.example.luckydomain.gateway.AwardGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class AwardListByParamQueryExe {
    private final AwardGateway awardGateway;

    public IPage<AwardVO> execute(AwardListByParamQuery query) {
        IPage<AwardEntity> page = awardGateway.page(query);

        return page.convert(AwardAssembler::toAwardVO);
    }
}
