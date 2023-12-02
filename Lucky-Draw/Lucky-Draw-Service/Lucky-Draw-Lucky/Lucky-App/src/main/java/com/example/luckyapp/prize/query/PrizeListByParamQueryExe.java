package com.example.luckyapp.prize.query;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.assembler.PrizeAssembler;
import com.example.luckyclient.dto.data.PrizeVO;
import com.example.luckyclient.dto.query.PrizeListByParamQuery;
import com.example.luckydomain.gateway.PrizeGateway;
import com.example.luckydomain.prize.PrizeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class PrizeListByParamQueryExe {

    private final PrizeGateway prizeGateway;

    public IPage<PrizeVO> execute(PrizeListByParamQuery query) {
        IPage<PrizeEntity> page = prizeGateway.page(query);

        return page.convert(PrizeAssembler::toPrizeVO);
    }
}
