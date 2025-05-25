package com.flash.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan({"com.flash.user.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
@CrossOrigin
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

	}

}
