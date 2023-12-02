package com.example.luckyclient.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.cmd.PrizeAddCmd;
import com.example.luckyclient.dto.cmd.PrizeUpdateCmd;
import com.example.luckyclient.dto.data.PrizeVO;
import com.example.luckyclient.dto.query.PrizeListByParamQuery;


public interface IPrizeService {

    /**
     * 添加
     * @param cmd
     * @return
     */
    PrizeVO add(PrizeAddCmd cmd);

    /**
     * 修改
     * @param cmd
     * @return
     */
    PrizeVO update(PrizeUpdateCmd cmd);

    /**
     * 查询
     * @param query
     * @return
     */
    IPage<PrizeVO> page(PrizeListByParamQuery query);

    /**
     * 查询
     * @param id
     * @return
     */
    PrizeVO one(Long id);
}
