package com.example.luckydrawconfig.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import com.example.luckydrawconfig.exception.Exception;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Slf4j
public class FileLoad {

    public static String read(String fileName) {
        String val = "";
        try {
            val = IoUtil.read(new FileInputStream(FileUtil.file(fileName)), CharEncoding.UTF_8);
        } catch (Exception | FileNotFoundException e) {
            //错误处理
            log.error("文件路径读取失败：{}", fileName, e);
            throw new Exception(String.format("文件路径读取失败: %s", fileName));
        }
        return val;
    }
}
