package org.team10.washcode.domain.order.entity.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
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

    public String getToken() { return refreshToken; }
}
