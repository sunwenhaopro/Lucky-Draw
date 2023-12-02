package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class RecordUpdateStatusCmd extends Command {

    /**
     *
     */
    @NotNull
    private Long id;

    /**
     * 状态（0，1，2，3）
     */
    private Integer state;
}
