package org.team10.washcode.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    /*
    Lettuce는 비동기, Jedis는 동기 -> Lettuce는 Jedis에 비해 성능이 좋기도 해서 Lettuce 사용을 권장
    Lettuce는 따로 의존성 추가 안해줘도 된다.
    LettuceConnectionFactory(host, port)가 Lettuce로 Redis 연결을 설정해주는 부분
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }
}
