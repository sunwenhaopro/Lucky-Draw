package com.example.luckydrawconfig.exception;


public class Exception extends RuntimeException {
    public Exception() {
    }
    public Exception(String message, Object... args) {
        super(String.format(message, args));
    }

    public Exception(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public Exception(Throwable cause) {
        super(cause);
    }

}
