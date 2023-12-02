package com.example.luckyclient.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RuleAddCmd  extends Command {

    /**
     * 规则名称
     */
    @NotNull(message = "请设置ruleName")
    private String ruleName;

    /**
     * 最大可参与次数
     */
    @NotNull(message = "请设置maxJoinNumber")
    private Integer maxJoinNumber;

    /**
     * 最大可中奖次数
     */
    @NotNull(message = "请设置maxWinningNumber")
    private Integer maxWinningNumber;



    /**
     *
     */ 

    private String creator;



}
