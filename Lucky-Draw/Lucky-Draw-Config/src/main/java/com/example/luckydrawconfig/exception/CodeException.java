package com.example.luckydrawconfig.exception;


import com.example.luckydrawconfig.vo.FailInfo;

public class CodeException extends RuntimeException {

    private Integer code;
    public Integer getCode() {
        return code;
    }

    public CodeException() {
    }

    public CodeException(Integer code, String message, Object... args) {
        super(String.format(message, args));
        this.code = code;
    }

    public CodeException(String message, Object... args) {
        super(String.format(message, args));
        this.code = FailInfo.DEFAULT_CODE;
    }

    public CodeException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
        this.code = FailInfo.DEFAULT_CODE;
    }

    public CodeException(Throwable cause) {
        super(cause);
        this.code = FailInfo.DEFAULT_CODE;
    }

}
