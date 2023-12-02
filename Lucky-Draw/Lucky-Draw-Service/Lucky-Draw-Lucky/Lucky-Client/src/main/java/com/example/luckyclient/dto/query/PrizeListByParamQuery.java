package com.example.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;


@Data
public class PrizeListByParamQuery extends PageQuery {

    private Long id;

    private String prizeName;

    private Integer type;
}
