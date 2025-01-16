package org.team10.washcode.domain.order.entity.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

@RedisHash("KakaoPayPgToken")
@AllArgsConstructor
public class KakaoPayPgToken {
    @Id
    private String key;          // Redis Key
    private Integer partnerOrderId; // Redis Value

    public Integer getPartnerOrderId() { return partnerOrderId; }
}
