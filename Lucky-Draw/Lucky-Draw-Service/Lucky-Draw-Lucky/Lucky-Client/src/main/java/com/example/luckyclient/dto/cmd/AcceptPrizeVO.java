package com.example.luckyclient.dto.cmd;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AcceptPrizeVO {

    private Long id;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     *
     */
    private LocalDateTime createTime;
}
