package com.example.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyapp.record.command.RecordAddCmdExe;
import com.example.luckyapp.record.command.RecordUpdateStatusCmdExe;
import com.example.luckyapp.record.query.RecordListByParamQueryExe;
import com.example.luckyclient.api.IRecordServer;
import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import com.example.luckyclient.dto.data.RecordVO;
import com.example.luckyclient.dto.query.RecordListByParamQuery;
import com.example.luckydrawconfig.util.AssertUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@AllArgsConstructor
public class RecordServerImpl implements IRecordServer {

    private final RecordAddCmdExe recordAddCmdExe;
    private final RecordListByParamQueryExe recordListByParamQueryExe;
    private final RecordUpdateStatusCmdExe recordUpdateStatusCmdExe;



    @Override
    public IPage<RecordVO> page(RecordListByParamQuery query) {
        return recordListByParamQueryExe.execute(query);
    }

    @Override
    public RecordVO add(RecordAddCmd cmd) {
        return recordAddCmdExe.execute(cmd);
    }

    @Override
    public Integer prizeType(Long recordId) {
        return getPrizeByRecordId(recordId).getPrizeType();
    }

    @Override
    public Boolean update(RecordUpdateStatusCmd cmd) {
        return recordUpdateStatusCmdExe.execute(cmd);
    }
    @Override
    public RecordVO getPrizeByRecordId(Long recordId) {
        final var recordQuery = new RecordListByParamQuery();
        recordQuery.setRecordId(recordId);
        List<RecordVO> recordVOList = recordListByParamQueryExe.execute(recordQuery).getRecords();
        AssertUtil.isTrue(CollUtil.isEmpty(recordVOList) || Objects.isNull(recordVOList.get(0)), "数据不存在！");
        return recordVOList.get(0);
    }


}
