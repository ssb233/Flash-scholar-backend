package com.flash.redis.config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Configuration
@Getter
@Setter
public class RedisConfig {
    String redisUri = "redis://120.46.63.223:6379";
    public RedisClient redisClient = RedisClient.create(redisUri);
    public StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
    public RedisCommands<String, String> redisCommands = redisConnection.sync();
}
