package com.example.luckyadapter.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IAwardService;
import com.example.luckyclient.dto.cmd.AwardAddCmd;
import com.example.luckyclient.dto.cmd.AwardUpdateCmd;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckyclient.dto.query.AwardListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/award")
public class AwardController {

    private final IAwardService awardService;

    @PostMapping("/add")
    public AwardVO add(@Validated @RequestBody AwardAddCmd cmd) {
        return awardService.add(cmd);
    }

    @PostMapping("/update")
    public AwardVO update(@Validated @RequestBody AwardUpdateCmd cmd) {
        return awardService.update(cmd);
    }

    @GetMapping("/one")
    public AwardVO one(@RequestParam("id") Long id) {
        return awardService.one(id);
    }

    @PostMapping("/page")
    public IPage<AwardVO> page(@RequestBody AwardListByParamQuery query) {
        return awardService.page(query);
    }


}
