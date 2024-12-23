package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.order.OrderlistResDTO;
import org.team10.washcode.entity.Pickup;

import java.util.List;
import java.util.Map;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {

//    @Query("SELECT ls.shop_name, p.id, p.status, p.created_at " +
//            "FROM LaundryShop ls " +
//            "JOIN FETCH Pickup p ON ls = p.laundryshop " +
//            "WHERE p.user = :userId")
//    List<OrderlistResDTO> getOrdersByUserId(@Param("userId") int userId);

//        @Query("SELECT ls.shop_name, p.id, p.status, p.created_at " +
//                "FROM LaundryShop ls " +
//                "JOIN Pickup p ON ls.id = p.laundryshop.id " +
//                "WHERE p.user.id = :userId")
//        List<OrderlistResDTO> getOrdersByUserId(@Param("userId") int userId);

    @Query("SELECT ls.shop_name, p.id, p.status, p.created_at " +
            "FROM LaundryShop ls " +
            "JOIN Pickup p ON ls.id = p.laundryshop.id " +
            "WHERE p.user.id = :userId")
    List<Object[]> findOrderListByUserId(@Param("userId") int userId);

}
