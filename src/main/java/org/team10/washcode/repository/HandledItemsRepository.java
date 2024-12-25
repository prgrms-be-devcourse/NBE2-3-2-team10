package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.HandledItems;

import java.util.List;

@Repository
public interface HandledItemsRepository extends JpaRepository<HandledItems, Long> {
    List<HandledItems> findAll(); //모든 데이터 가져오기

    List<HandledItems> findByLaundryshopId(Long laundryShopId);


}
