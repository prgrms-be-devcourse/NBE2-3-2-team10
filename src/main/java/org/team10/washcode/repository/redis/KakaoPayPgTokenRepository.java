package org.team10.washcode.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.redis.KakaoPayPgToken;

import java.util.Optional;

@Repository
public interface KakaoPayPgTokenRepository extends CrudRepository<KakaoPayPgToken, String> {
}
