package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.User;

import java.util.Optional;

import java.util.List;


@Repository
public interface LaundryShopRepository extends JpaRepository<LaundryShop, Long> {

    Optional<LaundryShop> findById(Long id);


    @Query("SELECT L FROM LaundryShop L WHERE L.shop_name like %:shop_name%")
    List<LaundryShop> findByShop_NameContaining(@Param("shop_name") String shop_name);

    List<LaundryShop> findByIdIn(List<Integer> ids);

    Optional<LaundryShop> findByUserId(int userId);

}
