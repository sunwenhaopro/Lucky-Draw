package com.example.luckyapp.service;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.award.command.AwardAddCmdExe;
import com.example.luckyapp.award.command.AwardUpdateCmdExe;
import com.example.luckyapp.award.query.AwardListByParamQueryExe;
import com.example.luckyclient.api.IAwardService;
import com.example.luckyclient.dto.cmd.AwardAddCmd;
import com.example.luckyclient.dto.cmd.AwardUpdateCmd;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckyclient.dto.query.AwardListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class AwardServiceImpl implements IAwardService {

    private final AwardAddCmdExe awardAddCmdExe;
    private final AwardUpdateCmdExe awardUpdateCmdExe;
    private final AwardListByParamQueryExe awardListByParamQueryExe;

    @Override
    public AwardVO add(AwardAddCmd cmd) {
        return awardAddCmdExe.execute(cmd);
    }

    @Override
    public AwardVO update(AwardUpdateCmd cmd) {
        return awardUpdateCmdExe.execute(cmd);
    }

    @Override
    public AwardVO one(Long id) {
        final var query = new AwardListByParamQuery();
        query.setId(id);
        IPage<AwardVO> page = page(query);

        if (CollectionUtil.isEmpty(page.getRecords())) {
            return null;
        }

        return page.getRecords().get(0);
    }

    @Override
    public IPage<AwardVO> page(AwardListByParamQuery query) {
        return awardListByParamQueryExe.execute(query);
    }
}
