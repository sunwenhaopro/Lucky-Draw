package com.example.LuckyDrawCommon.handler;

import com.example.luckydrawconfig.exception.CodeException;
import com.example.luckydrawconfig.exception.TokenAuthException;
import com.example.luckydrawconfig.vo.FailInfo;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.luckydrawconfig.exception.Exception;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class SystemExpectionHandler {

    @ExceptionHandler(value = java.lang.Exception.class)
    public FailInfo exception(java.lang.Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(ex.getMessage());
    }


    @ExceptionHandler(value = BindException.class)
    public FailInfo exception(BindException ex) {
        String defaultMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        log.error("Exception_info:{}", defaultMessage);
        log.error("Exception_info:", ex);
        return new FailInfo(defaultMessage);
    }

    @ExceptionHandler(value = CodeException.class)
    public FailInfo ldCodeException(CodeException ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public FailInfo sysException(java.lang.Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(ex.getMessage());
    }

    @ExceptionHandler(value = TokenAuthException.class)
    public FailInfo tokenAuthException(java.lang.Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(ex.getMessage());
    }


    @ExceptionHandler(value = MysqlDataTruncation.class)
    public FailInfo mysqlDataTruncation(java.lang.Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        return new FailInfo(500, ex.getMessage());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public FailInfo dataIntegrityViolationException(java.lang.Exception ex) {
        log.error("Exception_info:{}", ex.getMessage());
        log.error("Exception_info:", ex);
        String message = ex.getMessage();
        String[] split = message.split("\r\n###");
        for (String str : split) {
            if (str.trim().isBlank() || str.trim().contains("Error")){
                continue;
            }
            String[] split1 = str.split(":");
            if (split1.length > 0) {
                message = split1[split1.length - 1].trim();
            }
        }
        return new FailInfo(500, message);
    }
}
