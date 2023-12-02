package com.example.luckyapp.record.query;


import com.example.luckydomain.gateway.RecordGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Slf4j
@Component
@AllArgsConstructor
public class RecordMoneyParamQueryExe {
    private final RecordGateway recordGateway;

    public BigDecimal execute(Long recordId) {
        return recordGateway.getPrizeMoneyByRecordId(recordId);
    }
}
