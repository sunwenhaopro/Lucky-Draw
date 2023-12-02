package com.example.luckyapp.activity.command;

import com.example.luckyapp.mqproducer.ActivityDrawProducer;
import com.example.luckyapp.mqproducer.DrawNoticeProducer;
import com.example.luckyclient.dto.cmd.RecordAddCmd;
import com.example.luckyclient.dto.data.DrawResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class RedisRuleQueryExe {
    private final ActivityDrawProducer activityDrawProducer;
    private final DrawNoticeProducer drawNoticeProducer;
    @Resource
    private final RedisTemplate<String ,Object> redisTemplate;
    public Long addQuery(String key , RecordAddCmd recordAddCmd, DrawResultVO resultVO)
    {
        Long result= redisTemplate.opsForList().rightPush(key, recordAddCmd.getIsWinning());
        activityDrawProducer.send(recordAddCmd);
        drawNoticeProducer.send(resultVO);
        return result;
    }
    public List<Object> getQuery(String key)
    {
       if(Boolean.FALSE.equals(redisTemplate.hasKey(key)))
       {
           return List.of(0);
       }
       return  redisTemplate.opsForList().range(key,0,-1);
    }
    public Boolean deleteQuery(String key)
    {
        if(Boolean.TRUE.equals(redisTemplate.hasKey(key)))
        {
            redisTemplate.opsForList().rightPop(key);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
