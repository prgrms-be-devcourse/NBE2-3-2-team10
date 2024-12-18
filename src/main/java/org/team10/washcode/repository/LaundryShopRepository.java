package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.User;

import java.util.Optional;


@Repository
public interface LaundryShopRepository extends JpaRepository<LaundryShop, Long> {
    Optional<LaundryShop> findById(Long id);
}
