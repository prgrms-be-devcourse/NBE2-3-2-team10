package org.team10.washcode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.redis.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
}
