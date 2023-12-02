package com.example.luckyapp.record.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.assembler.RecordAssembler;
import com.example.luckyclient.dto.data.RecordVO;
import com.example.luckyclient.dto.query.RecordListByParamQuery;
import com.example.luckydomain.gateway.RecordGateway;
import com.example.luckydomain.record.RecordEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class RecordListByParamQueryExe {

    private final RecordGateway recordGateway;

    public IPage<RecordVO> execute(RecordListByParamQuery query) {
        IPage<RecordEntity> page = recordGateway.page(query);
        return page.convert(RecordAssembler::toRecordVO);
    }
}
