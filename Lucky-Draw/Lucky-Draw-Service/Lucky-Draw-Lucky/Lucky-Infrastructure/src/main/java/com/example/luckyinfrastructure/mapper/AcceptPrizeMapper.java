package com.example.luckyinfrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.luckyinfrastructure.dataobject.AcceptPrizeDB;
import org.apache.ibatis.annotations.Param;

public interface AcceptPrizeMapper extends BaseMapper<AcceptPrizeDB> {

    AcceptPrizeDB getByRecordId(@Param("recordId") Long recordId);
}




