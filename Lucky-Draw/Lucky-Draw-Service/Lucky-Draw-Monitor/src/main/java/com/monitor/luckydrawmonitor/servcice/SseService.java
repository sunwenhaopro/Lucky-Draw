package com.monitor.luckydrawmonitor.servcice;

import com.monitor.luckydrawmonitor.entity.*;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *@author CtrlCver
 *@data 2023/11/26
 *@description:
 * type : 1  抽奖结果
 * type : 2  异常结果
 * type : 3  排行榜
 * type : 4  访问趋势
 * type : 5  新用户数量
 * type : 6  设备记录
 * type : 7  经纬度


 *
 *
 *
 *
 */
@Service
public class SseService {

    public void sendDrawResult(DrawResult drawResult)  {
        if(!Record.hasSseLink) return;
        Result result=new Result();
        result.setType(1);
        result.setData(drawResult);
        try {
            Record.sseEmitterMap.get(5).send(result);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendExceptionInfo(ExceptionResult exceptionResult)  {
        if(!Record.hasSseLink) return;
        Result exceptionInfo=new Result();
        exceptionInfo.setType(2);
        exceptionInfo.setData(exceptionResult);
        try {
            Record.sseEmitterMap.get(5).send(exceptionInfo);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendRank(RankInfo rankInfo)
    {
        if(!Record.hasSseLink) return;
        Result rankResult=new Result();
        rankResult.setType(3);
        rankResult.setData(rankInfo);
        try {
           Record.sseEmitterMap.get(5).send(rankResult);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendAccessTrends(Map<Double,String> accessTrends)
    {
        if(!Record.hasSseLink) return;
        Result accessTrendsResult=new Result();
        accessTrendsResult.setType(4);
        accessTrendsResult.setData(accessTrends);
        try {
            Record.sseEmitterMap.get(5).send(accessTrendsResult);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendNewUserAndOnline(Long[] realTimeData)
    {
        if(!Record.hasSseLink) return;
        Result realTimeDataResult=new Result();
        realTimeDataResult.setType(5);
        realTimeDataResult.setData(realTimeData);
        try {
            Record.sseEmitterMap.get(5).send(realTimeDataResult);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendRecordInfo(RecordInfo record)
    {
        if(!Record.hasSseLink) return;
        Result recordInfo=new Result();
        recordInfo.setType(6);
        recordInfo.setData(record);
        try {
          Record.sseEmitterMap.get(5).send(recordInfo);
        }catch (IOException e) {
          e.printStackTrace();
        }
    }

    public void sendGnotes(List<Point> gnotes)
    {
        if(!Record.hasSseLink) return;
        Result gnoteResult=new Result();
        gnoteResult.setType(7);
        gnoteResult.setData(gnotes);
        try {
            Record.sseEmitterMap.get(5).send(gnoteResult);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
