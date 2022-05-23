package main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

// uncomment if you configure Redis session store
//@Configuration
//public class RedisConfig {
//    private final redis.embedded.RedisServer redisServer;
//
//    public RedisConfig(@Value("${spring.redis.port}") final int redisPort) throws IOException {
//        this.redisServer = new redis.embedded.RedisServer(redisPort);
//    }
//
//    @PostConstruct
//    public void startRedis() {
//        this.redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        this.redisServer.stop();
//    }
//}
