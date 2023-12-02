package com.example.luckyadapter.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.LuckyDrawCommon.annotation.ResponseResult;
import com.example.luckyclient.api.IRuleService;
import com.example.luckyclient.dto.cmd.RuleAddCmd;
import com.example.luckyclient.dto.cmd.RuleUpdateCmd;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckyclient.dto.query.RuleListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/rule")
public class RuleController {
    private final IRuleService ruleService;
     @PostMapping("/add")
     @SneakyThrows
    public RuleVO add(@Validated @RequestBody RuleAddCmd cmd)
     {
         cmd.setCreator("name");
         return ruleService.add(cmd);
     }

     @PostMapping("/update")
    public RuleVO update(@Validated @RequestBody RuleUpdateCmd cmd)
     {
         return ruleService.update(cmd);
     }

     @GetMapping("/one")
    public RuleVO one(@RequestParam("id") Long id)
     {
         return  ruleService.one(id);
     }

     @PostMapping("/page")
    public IPage<RuleVO> page(@Validated @RequestBody RuleListByParamQuery query)
     {
         return ruleService.page(query);
     }
}
