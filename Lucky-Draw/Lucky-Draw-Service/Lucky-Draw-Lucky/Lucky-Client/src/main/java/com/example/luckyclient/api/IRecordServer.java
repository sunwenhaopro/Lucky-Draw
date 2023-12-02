package com.example.luckyclient.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import com.example.luckyclient.dto.data.RecordVO;
import com.example.luckyclient.dto.query.RecordListByParamQuery;

public interface IRecordServer {

    IPage<RecordVO> page(RecordListByParamQuery query);

    RecordVO add(RecordAddCmd cmd);


    RecordVO getPrizeByRecordId(Long id);

    Integer prizeType(Long recordId);

    Boolean update(RecordUpdateStatusCmd cmd);
}
