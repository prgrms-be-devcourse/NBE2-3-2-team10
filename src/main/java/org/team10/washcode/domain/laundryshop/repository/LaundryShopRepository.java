package org.team10.washcode.domain.laundryshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.domain.laundryshop.entity.LaundryShop;

import java.util.Optional;

import java.util.List;


@Repository
public interface LaundryShopRepository extends JpaRepository<LaundryShop, Long> {

    @Query("SELECT L.shopName FROM LaundryShop L WHERE L.id = :id")
    Optional<String> findNameById(int id);

    @Query("SELECT L FROM LaundryShop L WHERE L.shopName like %:shopName% OR L.address like %:shopName%")
    List<LaundryShop> findByShopNameContaining(@Param("shop_name") String shopName);

    List<LaundryShop> findByIdIn(List<Integer> ids);

    Optional<LaundryShop> findByUserId(int userId);

    Optional<LaundryShop> findById(int id);

}
