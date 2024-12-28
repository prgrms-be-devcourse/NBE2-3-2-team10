package org.team10.washcode.entity.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "token")
@AllArgsConstructor
public class Token {
    @Id
    private int id;
    private String refreshToken;
    @TimeToLive
    private Long expiration;
}
