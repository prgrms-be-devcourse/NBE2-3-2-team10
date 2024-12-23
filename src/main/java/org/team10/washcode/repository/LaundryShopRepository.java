package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.LaundryShop;

import java.util.List;


@Repository
public interface LaundryShopRepository extends JpaRepository<LaundryShop, Long> {

    @Query("SELECT L FROM LaundryShop L WHERE L.shop_name like %:shop_name%")
    List<LaundryShop> findByShop_NameContaining(@Param("shop_name") String shop_name);

    @Query("SELECT L FROM LaundryShop L WHERE L.id = :id")
    LaundryShop findById(@Param("id") int id);
}
