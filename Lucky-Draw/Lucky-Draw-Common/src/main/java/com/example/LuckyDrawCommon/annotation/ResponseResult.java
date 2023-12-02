package com.example.LuckyDrawCommon.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@RestController // 组合
public @interface ResponseResult {
    boolean ignore() default false;
}
