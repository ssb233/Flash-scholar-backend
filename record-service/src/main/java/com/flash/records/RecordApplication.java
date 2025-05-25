package com.flash.records;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */
@SpringBootApplication
@MapperScan({"com.flash.records.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class RecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecordApplication.class, args);
    }
}
