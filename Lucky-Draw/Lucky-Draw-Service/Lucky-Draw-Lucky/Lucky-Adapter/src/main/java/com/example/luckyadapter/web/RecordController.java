package com.example.luckyadapter.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IRecordServer;
import com.example.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import com.example.luckyclient.dto.data.RecordVO;
import com.example.luckyclient.dto.query.RecordListByParamQuery;
import com.example.luckydrawconfig.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/record")
public class RecordController {

    private final IRecordServer recordServer;


    @GetMapping("/prizeType")
    public Integer prizeType(@RequestParam("recordId") Long recordId) {
        return recordServer.prizeType(recordId);
    }

    @PostMapping("/updateStatusTo4")
    public Boolean updateStatusTo4(@RequestBody @Validated RecordUpdateStatusCmd cmd) {
        cmd.setState(4);
        return recordServer.update(cmd);
    }

    @PostMapping ("/page")
    public IPage<RecordVO> page(@RequestBody RecordListByParamQuery query) {
        query.setUserId(SecurityUtil.getUserId());
        return recordServer.page(query);
    }

    @PostMapping("/updateStatusTo3")
    public Boolean updateStatusTo3(@RequestBody  RecordUpdateStatusCmd cmd) {
        cmd.setState(3);
        return recordServer.update(cmd);
    }

}
