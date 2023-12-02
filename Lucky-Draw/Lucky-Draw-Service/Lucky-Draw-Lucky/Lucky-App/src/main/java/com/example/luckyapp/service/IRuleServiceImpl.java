package com.example.luckyapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.rule.command.RuleAddCmdExe;
import com.example.luckyapp.rule.command.RuleUpdateCmdExe;
import com.example.luckyapp.rule.query.RuleListByParamQueryExe;
import com.example.luckyclient.api.IRuleService;
import com.example.luckyclient.dto.cmd.RuleAddCmd;
import com.example.luckyclient.dto.cmd.RuleUpdateCmd;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckyclient.dto.query.RuleListByParamQuery;
import com.example.luckydrawconfig.exception.Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class IRuleServiceImpl implements IRuleService {
    private final RuleAddCmdExe ruleAddCmdExe;
    private final RuleUpdateCmdExe ruleUpdateCmdExe;
    private final RuleListByParamQueryExe ruleListByParamQueryExe;
    @Override
    public RuleVO add(RuleAddCmd cmd) {
        return ruleAddCmdExe.execute(cmd);
    }

    @Override
    public RuleVO update(RuleUpdateCmd cmd) {
        return ruleUpdateCmdExe.execute(cmd);
    }

    @Override
    public RuleVO one(Long id) {
           RuleListByParamQuery query=new RuleListByParamQuery();
           query.setId(id);
           IPage<RuleVO> page=ruleListByParamQueryExe.execute(query);
           if(Objects.isNull(page))
           {
               throw new Exception("rule不存在");
           }
           return page.getRecords().get(0);
    }

    @Override
    public IPage<RuleVO> page(RuleListByParamQuery query) {
        return ruleListByParamQueryExe.execute(query);
    }


}
