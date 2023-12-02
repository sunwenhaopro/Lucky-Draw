package com.example.luckyclient.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.cmd.RuleAddCmd;
import com.example.luckyclient.dto.cmd.RuleUpdateCmd;
import com.example.luckyclient.dto.data.RuleVO;
import com.example.luckyclient.dto.query.RuleListByParamQuery;

public interface IRuleService {

       RuleVO add(RuleAddCmd cmd);

       RuleVO update(RuleUpdateCmd cmd);

       RuleVO  one (Long  id);

       IPage<RuleVO> page(RuleListByParamQuery query);
}
