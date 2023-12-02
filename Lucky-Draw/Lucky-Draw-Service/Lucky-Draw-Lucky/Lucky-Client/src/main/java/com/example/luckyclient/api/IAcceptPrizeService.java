package com.example.luckyclient.api;


import com.example.luckyclient.dto.cmd.AcceptPrizeVO;
import com.example.luckyclient.dto.query.AcceptPrizeAddCmd;

public interface IAcceptPrizeService {

    AcceptPrizeVO one(Long recordId);

    AcceptPrizeVO add(AcceptPrizeAddCmd cmd);
}
