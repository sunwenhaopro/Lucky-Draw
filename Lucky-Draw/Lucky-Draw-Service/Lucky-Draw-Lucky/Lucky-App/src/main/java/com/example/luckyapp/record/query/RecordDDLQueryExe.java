package com.example.luckyapp.record.query;

import com.example.luckyclient.dto.query.RecordWarnQuery;
import com.example.luckydomain.gateway.RecordGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RecordDDLQueryExe {
    private final RecordGateway recordGateway;
    public List<RecordWarnQuery> findAllDdl()
    {
        return  recordGateway.findAllDdl();
    }
}
