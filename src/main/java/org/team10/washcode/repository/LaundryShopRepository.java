package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.LaundryShop;

import java.util.List;


@Repository
public interface LaundryShopRepository extends JpaRepository<LaundryShop, Long> {
    //List<LaundryShop> findByShop_nameContaining(String shop_name);
}
