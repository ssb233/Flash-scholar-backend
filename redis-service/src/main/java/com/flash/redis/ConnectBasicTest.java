package com.flash.redis;

import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class ConnectBasicTest {

    public static void connectBasic() {
        RedisURI uri = RedisURI.Builder
                .redis("120.46.63.223", 6379)
                .build();

        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();

        commands.set("foo12", "bar12");
        String result = commands.get("foo12");
        System.out.println(result); // >>> bar

        connection.close();

        client.shutdown();
    }

    public static void main(String[] args) {
        connectBasic();
    }
}