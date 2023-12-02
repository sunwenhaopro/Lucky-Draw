package com.example.luckyadapter.web;

import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IAcceptPrizeService;
import com.example.luckyclient.dto.cmd.AcceptPrizeVO;
import com.example.luckyclient.dto.query.AcceptPrizeAddCmd;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/acceptPrize")
public class AcceptPrizeController {

    private final IAcceptPrizeService acceptPrizeService;

    @PostMapping("/add")
    public AcceptPrizeVO add(@Validated @RequestBody AcceptPrizeAddCmd cmd) {
        return acceptPrizeService.add(cmd);
    }

    @GetMapping("/one")
    public AcceptPrizeVO one(@RequestParam("recordId") Long recordId) {
        return acceptPrizeService.one(recordId);
    }

}
