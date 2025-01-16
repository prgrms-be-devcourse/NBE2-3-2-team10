package org.team10.washcode.domain.order.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.domain.order.entity.redis.KakaoPayPgToken;

@Repository
public interface KakaoPayPgTokenRepository extends CrudRepository<KakaoPayPgToken, String> {
}
