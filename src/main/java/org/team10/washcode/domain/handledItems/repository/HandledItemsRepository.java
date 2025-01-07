package org.team10.washcode.domain.handledItems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.global.comm.enums.LaundryCategory;
import org.team10.washcode.domain.handledItems.dto.ItemInfoResDTO;
import org.team10.washcode.domain.handledItems.entity.HandledItems;

import java.util.List;
import java.util.Optional;

@Repository
public interface HandledItemsRepository extends JpaRepository<HandledItems, Long> {
    List<HandledItems> findAll(); //모든 데이터 가져오기

    //List<HandledItems> findByLaundryshopId(Long laundryShopId);


    //세탁소 아이디를 받아서 handledItem 내용 조회(id, laundryshop_id,price, item_name, category)
    @Query("SELECT h FROM HandledItems h WHERE h.laundryshop.id = :laundryshopId")
    List<HandledItems> findByLaundryshopId(@Param("laundryshopId") Long laundryshopId);

    //카테고리별로 세탁소 id 조회
    @Query("SELECT h.laundryshop.id FROM HandledItems h WHERE h.category = :category")
    List<Integer> findLaundryShopIdsByCategory(LaundryCategory category);

    @Query("SELECT new org.team10.washcode.ResponseDTO.laundry.ItemInfoResDTO(h.id, h.item_name, h.category,h.price) FROM HandledItems h WHERE h.laundryshop.id = :laundryId")
    List<ItemInfoResDTO> findHandledItemsByLaundryId(@Param("laundryId") int laundryId);

    Optional<HandledItems> findById(@Param("itemId") int itemId);
}
