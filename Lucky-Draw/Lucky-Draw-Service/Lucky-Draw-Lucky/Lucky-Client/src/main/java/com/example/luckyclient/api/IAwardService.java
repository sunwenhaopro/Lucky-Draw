package com.example.luckyclient.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.cmd.AwardAddCmd;
import com.example.luckyclient.dto.cmd.AwardUpdateCmd;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckyclient.dto.query.AwardListByParamQuery;


public interface IAwardService {

    AwardVO add(AwardAddCmd cmd);

    AwardVO update(AwardUpdateCmd cmd);

    AwardVO one(Long id);

    IPage<AwardVO> page(AwardListByParamQuery query);
}
