package com.example.luckyapp.listener;

import com.example.luckyclient.dto.data.ActivityConfigVO;
import com.example.luckyclient.dto.data.AwardVO;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AwardInventoryToRedisApplicationListener implements ApplicationListener<ActivityCreatEvent> {
    private final RedisTemplate<String,Object> redisTemplate;
    private final static String key="sun";
    @Override
    public void onApplicationEvent(ActivityCreatEvent event) {
        ActivityConfigVO activityConfigVO = event.getActivityConfigVO();
        for(AwardVO awardVO : activityConfigVO.getAwardVOList())
        {
            redisTemplate.opsForValue().set(key+":"+activityConfigVO.getActivityVO().getId()+":"+awardVO.getId(),awardVO.getNumber());
        }
    }
}
