package com.flash.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ElasticSearchService {
    @Autowired
    private WebClient webClient;

    public String searchInES(String requestBody) {
        String url = "http://120.46.63.223:9200/works/_search";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            System.out.println(jsonNode);
            return webClient.post()
                    .uri(url)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .bodyValue(jsonNode)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // 阻塞操作获取响应结果
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // 发送 POST 请求
    }
}
