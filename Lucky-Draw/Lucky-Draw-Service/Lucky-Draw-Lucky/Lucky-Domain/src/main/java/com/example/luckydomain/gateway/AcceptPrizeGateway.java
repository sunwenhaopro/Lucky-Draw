package com.example.luckydomain.gateway;


import com.example.luckydomain.acceptprize.AcceptPrizeEntity;

public interface AcceptPrizeGateway {

    AcceptPrizeEntity save(AcceptPrizeEntity toEntity);

    AcceptPrizeEntity one(Long recordId);
}
