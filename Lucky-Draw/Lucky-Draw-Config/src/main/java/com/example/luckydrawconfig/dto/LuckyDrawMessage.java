package com.example.luckydrawconfig.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LuckyDrawMessage {
    private Long id;
    private String uuid;

    /**
     * JSON内容对象
     */
    private String body;
}
