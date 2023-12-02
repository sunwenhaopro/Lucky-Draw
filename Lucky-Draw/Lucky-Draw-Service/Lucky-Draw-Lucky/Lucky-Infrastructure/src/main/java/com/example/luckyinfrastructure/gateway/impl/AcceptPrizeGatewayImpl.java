package com.example.luckyinfrastructure.gateway.impl;


import com.example.luckydomain.acceptprize.AcceptPrizeEntity;
import com.example.luckydomain.gateway.AcceptPrizeGateway;
import com.example.luckydrawconfig.util.AssertUtil;
import com.example.luckydrawconfig.vo.ExceptionEnum;
import com.example.luckyinfrastructure.convertor.AcceptPrizeConvertor;
import com.example.luckyinfrastructure.dataobject.AcceptPrizeDB;
import com.example.luckyinfrastructure.mapper.AcceptPrizeMapper;
import com.example.luckyinfrastructure.mapper.RecordMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class AcceptPrizeGatewayImpl implements AcceptPrizeGateway {

    private final AcceptPrizeMapper acceptPrizeMapper;
    private final RecordMapper recordMapper;

    @Override
    public AcceptPrizeEntity save(AcceptPrizeEntity toEntity) {
        AcceptPrizeDB acceptPrizeDB = AcceptPrizeConvertor.toAcceptPrizeDB(toEntity);
        AssertUtil.isTrue(acceptPrizeMapper.insert(acceptPrizeDB) <= 0, ExceptionEnum.ADD_ERROR.getDescription());

        return AcceptPrizeConvertor.toEntity(acceptPrizeDB);
    }

    @Override
    public AcceptPrizeEntity one(Long recordId) {
        AcceptPrizeDB acceptPrizeDB = acceptPrizeMapper.getByRecordId(recordId);
        return AcceptPrizeConvertor.toEntity(acceptPrizeDB);
    }
}
