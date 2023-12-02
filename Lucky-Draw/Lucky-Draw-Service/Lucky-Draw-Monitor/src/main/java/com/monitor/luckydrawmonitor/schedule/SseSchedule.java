package com.monitor.luckydrawmonitor.schedule;

import com.example.luckydrawconfig.util.SseUtil;
import com.monitor.luckydrawmonitor.entity.Record;
import com.monitor.luckydrawmonitor.entity.RecordInfo;

import com.monitor.luckydrawmonitor.entity.RankInfo;
import com.monitor.luckydrawmonitor.servcice.RedisService;
import com.monitor.luckydrawmonitor.servcice.SseService;
import org.springframework.data.geo.Point;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@EnableAsync
@Component
public class SseSchedule {
    @Resource
    RedisService redisService;
    @Resource
    SseService sseService;

    @Async
    @Scheduled(cron="0/1 * * * * ?") //每秒发送一次
    public void sendRealTimeData(){
        if(!Record.hasSseLink) return;
        Long[] realTimeData =new Long[3];
        long newUserNumber=Record.newUserNumber.longValue();
        realTimeData[0]=newUserNumber;
        realTimeData[1]= (long) SseUtil.getInstance().getSseEmitterMap().size();
        realTimeData[2]= redisService.getUV();
        sseService.sendNewUserAndOnline(realTimeData);
    }

    @Scheduled(cron="1 0/1 * * * ?") //每分钟发送给一次
    public void sendRecordInfo(){
        if(!Record.hasSseLink) return;
        RecordInfo recordInfo=new RecordInfo();
        Map<String,Long> map1 = new LinkedHashMap<>(5);
        Map<String,Long> map2= new LinkedHashMap<>(5);
        Map<String,Long> map3 = new LinkedHashMap<>(5);
        Map<String,Long> map4= new LinkedHashMap<>(5);
        Record.browser.forEach((k,v)->{
            map1.put(k,v.longValue());
        });
        Record.mediaTypeNumber.forEach((k,v)->{
            map2.put(k,v.longValue());
        });
        Record.status.forEach((k,v)->{
            map3.put(k,v.longValue());
        });
        map3.put("uv",redisService.getUV());
        Record.requestStatus.forEach((k,v)->{
            map4.put(k,v.longValue());
        });
        recordInfo.setStatusMap(map3);
        recordInfo.setBrowserMap(map1);
        recordInfo.setMediaMap(map2);
        recordInfo.setRequestStatusMap(map4);
        sseService.sendRecordInfo(recordInfo);
    }
    @Scheduled( cron= "2 0/2 * * * ?") //2分钟一次
    public void sendRank(){
        if(!Record.hasSseLink) return;
        RankInfo rankInfo=new RankInfo();
        Map<String,Double> interfaceRank=redisService.getInterfaceRank();
        Set<String> cityRank=redisService.getCityRank();
        Set<String> provinceRank=redisService.getProvinceRank();
        Set<String> countryRank=redisService.getCountryRank();
        rankInfo.setCityRank(cityRank);
        rankInfo.setInterfaceRank(interfaceRank);
        rankInfo.setProvinceRank(provinceRank);
        rankInfo.setCountryRank(countryRank);
        sseService.sendRank(rankInfo);
    }
    @Scheduled(cron="10 0/5 * * * ?") //每5分钟发送一次
    public void sendAccessTrends(){
        if(!Record.hasSseLink) return;
        Map<Double,String> accessTrends=redisService.getAccessTrends();
        sseService.sendAccessTrends(accessTrends);
    }

    @Scheduled(cron="3 0/10 * * * ?") //每10分钟发送一次
    public void sendGnote(){
        if(!Record.hasSseLink) return;
        List<Point> gnote=redisService.getGnote();
        sseService.sendGnotes(gnote);
    }

    @Scheduled(cron="4 0/5 * * * ?") //每5分钟记录一次
    public void recordAccessTrends(){
        redisService.recordAccessTrends();
    }

    @Scheduled(cron="50 59 23 * * ?") // 0点把今天记录存进redis持久化
    public void recordInterfaceInfo(){
        redisService.recordAllInfo();
    }

    @Scheduled(cron="0 0 0 * * ?") //每天0点更新日期
    public void changeDate(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Record.lastDate=Record.date;
        Record.date=format.format(System.currentTimeMillis());
    }
}
