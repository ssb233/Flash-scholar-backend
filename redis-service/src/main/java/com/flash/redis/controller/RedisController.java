package com.flash.redis.controller;


import com.flash.redis.DTO.GetRedisDTO;
import com.flash.redis.DTO.PressureDTO;
import com.flash.redis.DTO.RedisDTO;
import com.flash.redis.response.CustomResponse;
import com.flash.redis.service.ElasticSearchService;
import com.flash.redis.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.flash.redis.service.RedisService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/redis")
public class RedisController {
    @Autowired
    RedisService redisService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/set")
    public CustomResponse setValue(@RequestBody RedisDTO redisDTO) {
        CustomResponse customResponse = new CustomResponse();
        String key = redisDTO.getKey();
        String value = redisDTO.getValue();
        try {
            redisService.setValue(key,value);
            customResponse.setMessage("添加成功");
        }catch (BusinessException e){
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/get")
    public CustomResponse getValue(@RequestBody GetRedisDTO getRedisDTO) {
        CustomResponse customResponse = new CustomResponse();
        String key = getRedisDTO.getKey();
        try {
            List<String> ans = new ArrayList<>();
            ans = redisService.getValue(key);
            customResponse.setMessage("查询成功");
            customResponse.setData(ans);
        }catch (BusinessException e){
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("/test")
    public CustomResponse search(@RequestParam String tmp) {

        CustomResponse customResponse = new CustomResponse();
        String requestBody = tmp;
        System.out.println(requestBody);
        try {
            List<String> ans = null;
            System.out.println(requestBody);
            ans = redisService.getValue(requestBody);
            System.out.println(ans);
            if (ans.get(0) == null) {
                System.out.println("redis失败");
                ans.add(elasticSearchService.searchInES(requestBody));
                redisService.setValue(requestBody,ans.get(0));
            }
            customResponse.setMessage("查询成功");
            customResponse.setData(ans);
        }catch (BusinessException e){
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}
