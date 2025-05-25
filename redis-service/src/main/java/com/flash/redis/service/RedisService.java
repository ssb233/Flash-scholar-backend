package com.flash.redis.service;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.flash.redis.config.RedisConfig;
import com.flash.redis.utils.BusinessException;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {
    private RedisConfig redisConfig = new RedisConfig();

    public void setValue(String key, String value) throws BusinessException{

        redisConfig.getRedisCommands().set(key, value);
    }

    public List<String> getValue(String key) throws BusinessException {
        List<String> ans = new ArrayList<>();
        // 记录开始时间
        long startTime = System.nanoTime();
        String tmp = redisConfig.getRedisCommands().get(key);
        // 记录结束时间
        long endTime = System.nanoTime();
        // 计算查询耗时（单位：毫秒）
        long duration = (endTime - startTime) / 1000000;
        System.out.println(duration);
        String cost = ""+duration;
        ans.add(tmp);
        ans.add(cost);
        return ans;
    }

}
