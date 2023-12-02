package com.example.luckydomain.rule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RuleEntity {

    private Long id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 最大可参与次数
     */
    private MaxJoinNumber  maxJoinNumber;

    /**
     * 最大可中奖次数
     */
    private MaxWinNumber maxWinNumber;

    /**
     *
     */

    private LocalDateTime createTime;

    /**
     *
     */
    private String creator;

    /**
     *
     */

    private LocalDateTime updateTime;


}
