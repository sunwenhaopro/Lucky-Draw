package com.example.luckyapp.activity.command;


import com.example.luckyapp.mqproducer.ActivityDrawProducer;
import com.example.luckyclient.dto.data.ActivityConfigVO;
import com.example.luckyclient.dto.data.AwardVO;
import com.example.luckydrawconfig.util.FileLoad;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class RedisDeductionAwardNumberDrawExe{
      private final RedisTemplate<String ,Object>  redisTemplate;
      private final ActivityDrawProducer activityDrawProducer;
      private static String stock_deduction;
      private static String stock_rollback;
      static {
            try {
                  stock_deduction = FileLoad.read( "lua//stock_deduction.lua");
                  stock_rollback = FileLoad.read( "lua//stock_rollback.lua");
                  log.info("——————————lua脚本加载成功————————");
            }catch (Exception e)
            {
                  System.out.println("lua脚本加载出错");
            }
      }
      public Integer deductionAwardNumber(ActivityConfigVO activityConfigVO, AwardVO awardVO)
      {
            RedisScript<Long> redisScript=new DefaultRedisScript<>(stock_deduction, Long.class);
            String key="sun"+":"+activityConfigVO.getActivityVO().getId()+":"+awardVO.getId();
            Long execute= redisTemplate.opsForValue().getOperations().execute(redisScript, List.of(key));
            Integer  deductionLua= Objects.isNull(execute) || execute == -1? 0:1;
            return deductionLua;
      }

      public Integer invokeStockRollbackLua(String key) {
            RedisScript<Long> redisScript = new DefaultRedisScript<>(stock_rollback, Long.class);

            Long execute = redisTemplate.opsForValue().getOperations().execute(
                    redisScript,
                    List.of(key));

            if (Objects.isNull(execute) || execute < 0) {
                  return 0;
            }
            return 1;
      }
}
