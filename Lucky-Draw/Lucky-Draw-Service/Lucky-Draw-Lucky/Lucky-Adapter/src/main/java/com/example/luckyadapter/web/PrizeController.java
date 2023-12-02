package com.example.luckyadapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IPrizeService;
import com.example.luckyclient.dto.cmd.PrizeAddCmd;
import com.example.luckyclient.dto.cmd.PrizeUpdateCmd;
import com.example.luckyclient.dto.data.PrizeVO;
import com.example.luckyclient.dto.query.PrizeListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@ResponseResult
@AllArgsConstructor
@RequestMapping("/v1/prize")
public class PrizeController {
    private final IPrizeService prizeService;
    @SneakyThrows
    @PostMapping("/add")
    public PrizeVO add(@Validated @RequestBody PrizeAddCmd cmd) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
         String name = URLDecoder.decode(request.getHeader("UserName"), StandardCharsets.UTF_8.toString());
         cmd.setCreator(name);
         return prizeService.add(cmd);
    }

    @PostMapping("/update")
    public PrizeVO add(@Validated @RequestBody PrizeUpdateCmd cmd)
    {
        return prizeService.update(cmd);
    }

    @GetMapping("/one")
    public PrizeVO one(  @RequestParam("id") Long id)
    {
        return prizeService.one(id);
    }

    @PostMapping("/page")
    public IPage<PrizeVO> page( @RequestBody PrizeListByParamQuery query){
        return prizeService.page(query);
    }
}
