package org.team10.washcode.domain.order.entity.redis;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("KakaoPaymentInfo")
@AllArgsConstructor
public class KakaoPaymentInfo {
    @Id
    private String key;  // Redis에서 사용할 키 (userId + ":" + cid)
    private int partnerOrderId;
    private String tid;

    public Integer getPartnerOrderId() { return partnerOrderId; }

    public String getTid() { return tid; }
}
