package com.example.luckyclient.dto.query;



import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import java.util.List;

@Data
public class RuleListByParamQuery extends PageQuery {
    private Long id;

    private List<Long> ids;

    private String ruleName;
}
