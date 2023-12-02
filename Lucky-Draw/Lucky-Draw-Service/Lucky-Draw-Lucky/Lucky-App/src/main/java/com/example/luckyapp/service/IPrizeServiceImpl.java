package com.example.luckyapp.service;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.prize.command.PrizeAddCmdExe;
import com.example.luckyapp.prize.command.PrizeUpdateCmdExe;
import com.example.luckyapp.prize.query.PrizeListByParamQueryExe;
import com.example.luckyclient.api.IPrizeService;
import com.example.luckyclient.dto.cmd.PrizeAddCmd;
import com.example.luckyclient.dto.cmd.PrizeUpdateCmd;
import com.example.luckyclient.dto.data.PrizeVO;
import com.example.luckyclient.dto.query.PrizeListByParamQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class IPrizeServiceImpl implements IPrizeService {

    private final PrizeAddCmdExe prizeAddCmdExe;
    private final PrizeUpdateCmdExe prizeUpdateCmdExe;
    private final PrizeListByParamQueryExe prizeListByParamQueryExe;

    @Override
    public PrizeVO add(PrizeAddCmd cmd) {
        return prizeAddCmdExe.execute(cmd);
    }

    @Override
    public PrizeVO update(PrizeUpdateCmd cmd) {
        return prizeUpdateCmdExe.execute(cmd);
    }

    @Override
    public IPage<PrizeVO> page(PrizeListByParamQuery query) {
        return prizeListByParamQueryExe.execute(query);
    }

    @Override
    public PrizeVO one(Long id) {
        final var query = new PrizeListByParamQuery();
        query.setId(id);
        IPage<PrizeVO> page = page(query);
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return null;
        }

        return page.getRecords().get(0);
    }
}
