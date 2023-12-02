package com.example.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;

import lombok.Data;

@Data
public class UserListByParamQuery extends PageQuery {
    private Long id;

    private String name;

    private String mail;
}
