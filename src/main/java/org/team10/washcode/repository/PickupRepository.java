package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.order.OrderlistResDTO;
import org.team10.washcode.ResponseDTO.order.OrderResDTO;
import org.team10.washcode.entity.Pickup;

import java.util.List;
import java.util.Map;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {
    //이용내역 조회
    @Query("SELECT ls.shop_name, p.id, p.status, p.created_at " +
            "FROM LaundryShop ls " +
            "JOIN Pickup p ON ls.id = p.laundryshop.id " +
            "WHERE p.user.id = :userId")
    List<Object[]> findOrderListByUserId(@Param("userId") int userId);

    //이용내역 조회(상세보기)
        @Query("SELECT " +
                "u.address AS address, " +
                "u.phone AS phone, " +
                "ls.shop_name AS shopName, " +
                "p.id AS pickupId, " +
                "p.status AS status, " +
                "p.content AS content, " +
                "p.created_at AS pickupCreatedAt, " +
                "p.update_at AS pickupUpdateAt, " +
                "pi.id AS pickupItemId, " +
                "pi.quantity AS quantity, " +
                "pi.totalPrice AS totalPrice, " +
                "hi.item_name AS itemName, " +
                "hi.category AS category, " +
                "pay.amount AS amount, " +
                "pay.method AS method " +
                "FROM User u " +
                "JOIN Pickup p ON u.id = p.user.id " +
                "JOIN LaundryShop ls ON ls.id = p.laundryshop.id " +
                "LEFT JOIN PickupItem pi ON p.id = pi.pickup.id " +
                "LEFT JOIN HandledItems hi ON pi.handledItems.id = hi.id " +
                "LEFT JOIN Payment pay ON p.id = pay.pickup.id " +
                "WHERE u.id = :userId AND pi.pickup.id = :pickupId")
        List<Object[]> findOrderDetails(
                @Param("userId") int userId,
                @Param("pickupId") int pickupId);



}
