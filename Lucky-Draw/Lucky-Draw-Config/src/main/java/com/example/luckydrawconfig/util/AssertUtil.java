package com.example.luckydrawconfig.util;


import com.example.luckydrawconfig.exception.Exception;

public class AssertUtil {

    /**
     * 条件是 true，抛出消息
     * @param expression
     * @param message
     */
    public static void isTrue(Boolean expression, String message) {
        if (expression) {
            throw new Exception(message);
        }
    }

    /**
     * 条件是 false，抛出消息
     * @param expression
     * @param message
     */
    public static void isFalse(Boolean expression, String message) {
        if (!expression) {
            throw new Exception(message);
        }
    }
}
