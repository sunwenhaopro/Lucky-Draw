package com.monitor.luckydrawmonitor.entity;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class RankInfo {
    private Set<String> cityRank;
    private Set<String> provinceRank;
    private Set<String> countryRank;
    private Map<String,Double> interfaceRank;
}
