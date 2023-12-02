package com.monitor.luckydrawmonitor.entity;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Data
public class Record {
    public static Boolean hasSseLink=false;
    public static Map<Integer,SseEmitter> sseEmitterMap=new ConcurrentHashMap<>();

    public static  String date;
    public static String lastDate=null;
    //pv
    public static LongAdder pv=new LongAdder();

    public static LongAdder success=new LongAdder();
    public static LongAdder fail=new LongAdder();

    // 媒体类型统计
    public static Map<String ,LongAdder> mediaTypeNumber=new Hashtable<>(2);

    // resquest统计
    public static Map<String,LongAdder> requestStatus =new Hashtable<>(2);
    //浏览器类型统计
    public  static Map<String,LongAdder> browser=new Hashtable<>(5);

    //状态统计
    public  static Map<String,LongAdder> status=new Hashtable<>(5);

    //新注册人数
    public  static AtomicLong newUserNumber=new AtomicLong(50);

    //接口排行
    public static Map<String ,Double> interfaceRank=new LinkedHashMap<>();

    //城市
    public static Map<String,Long> cityRank=new LinkedHashMap<>();

    //省
    public static Map<String,Long> provinceRank=new LinkedHashMap<>();

    //国家
    public static Map<String,Long> countryRank=new LinkedHashMap<>();

    //访问趋势
    public static Map<Double, String> accessTrends=new LinkedHashMap<>();

    static {
        browser.put("Chrome",new LongAdder());
        browser.put("Edge",new LongAdder());
        browser.put("Firefox",new LongAdder());
        browser.put("Safari",new LongAdder());

        browser.put("Others",new LongAdder());
        browser.get("Edge").increment();
        browser.get("Edge").increment();
        browser.get("Edge").increment();
        browser.get("Edge").increment();

        status.put("pv",pv);
        status.put("uv",new LongAdder());
        status.put("error",new LongAdder());
        status.put("exception",new LongAdder());
        status.put("warn",new LongAdder());

        requestStatus.put("success",success);
        requestStatus.put("fail",fail);

        mediaTypeNumber.put("PC",new LongAdder());
        mediaTypeNumber.put("Mobile",new LongAdder());

        success.increment();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Record.date=format.format(System.currentTimeMillis());
    }
}
