package com.monitor.luckydrawmonitor.entity;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class AllInfo {
    private Long newUserNumber;
    private Map<String,Long> browser;
    private Map<String,Long> mediaType;
    private Map<String,Long> status;
    private Map<String,Double> interfaceRank;
    private Set<String> provinceRank;
    private Set<String> cityRank;
    private Set<String> countryRank;
    private Map<Double, String> accessTrends;
    private String date;

    @Override
    public String toString() {
        return "AllInfo{" +
                "newUserNumber=" + newUserNumber +
                ", browser=" + browser +
                ", mediaType=" + mediaType +
                ", status=" + status +
                ", interfaceRank=" + interfaceRank +
                ", provinceRank=" + provinceRank +
                ", cityRank=" + cityRank +
                ", countryRank=" + countryRank +
                ", accessTrends=" + accessTrends +
                ", date='" + date + '\'' +
                '}';
    }
}
