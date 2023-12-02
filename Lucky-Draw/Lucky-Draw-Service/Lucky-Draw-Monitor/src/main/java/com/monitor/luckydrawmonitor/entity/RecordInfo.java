package com.monitor.luckydrawmonitor.entity;

import lombok.Data;

import java.util.Map;
@Data
public class RecordInfo {
    private Map<String,Long> BrowserMap;
    private Map<String,Long> mediaMap;
    private Map<String,Long> statusMap;
    private Map<String,Long> requestStatusMap;
}
