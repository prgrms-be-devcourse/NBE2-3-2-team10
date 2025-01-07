package org.team10.washcode.domain.pickup.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.sql.Timestamp;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.global.comm.enums.PickupStatus;
import org.team10.washcode.domain.pickup.entity.Pickup;

import java.util.List;
import java.util.Optional;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {

    @Query("SELECT DISTINCT p FROM Pickup p " +
            "JOIN FETCH p.user u " +
            "JOIN FETCH p.laundryshop l " +
            "WHERE p.id = :pickupId")
    Optional<Pickup> findPickupWithFetchJoin(@Param("pickupId") long pickupId);

    @Query("SELECT DISTINCT p FROM Pickup p " +
            "JOIN FETCH p.user u " +
            "JOIN FETCH p.laundryshop l " +
            "WHERE p.laundryshop.user.id = :userId " +
            "AND p.status = :status")
    List<Pickup> findAllByUserIdWithFetchJoinAndStatus(@Param("userId") long userId,
                                                       @Param("status") PickupStatus status);

    @Query("SELECT DISTINCT p FROM Pickup p " +
            "JOIN FETCH p.user u " +
            "WHERE p.laundryshop.user.id = :userId " +
            "AND p.status IN :statuses " +
            "ORDER BY p.update_at DESC")
    List<Pickup> findAllByUserIdAndStatuses(@Param("userId") long userId,
                                            @Param("statuses") List<PickupStatus> statuses);

    //이용내역 조회
    @Query("SELECT ls.shop_name, p.id, p.status, p.created_at " +
            "FROM LaundryShop ls " +
            "JOIN Pickup p ON ls.id = p.laundryshop.id " +
            "WHERE p.user.id = :userId " +
            "ORDER BY p.created_at DESC" )
    List<Object[]> findOrderListByUserId(@Param("userId") int userId);


    // 필터링된 데이터 가져오기(개월 수)
    @Query("SELECT ls.shop_name, p.id, p.status, p.created_at " +
            "FROM LaundryShop ls " +
            "JOIN Pickup p ON ls.id = p.laundryshop.id " +
            "WHERE p.user.id = :userId AND p.created_at >= :fromDate")
    List<Object[]> findByUserIdAndDate(@Param("userId") int userId, @Param("fromDate") Timestamp fromDate);


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

            "pay.method AS method, " +
            "u.name AS name, " +
            "pay.payment_datetime AS paymentDateTime, " +
            "pay.id AS paymentId " +

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


    //취소버튼 ->status 상태 변경
    @Transactional
    @Modifying
    @Query("UPDATE Pickup p SET p.status = 'CANCELLED' WHERE p.id = :pickupId AND p.user.id = :userId")
    int cancleOrder(int pickupId, int userId);


    @Query("SELECT DISTINCT p FROM Pickup p " +
            "JOIN FETCH p.user u " +
            "JOIN FETCH p.laundryshop l " +
            "WHERE p.laundryshop.user.id = :userId " +
            "AND p.status IN :statuses " +
            "AND YEAR(p.created_at) = :year " +
            "AND MONTH(p.created_at) = :month " +
            "ORDER BY p.created_at DESC")
    List<Pickup> findSalesSummeryByUserIdAndDate(@Param("userId") long userId,
                                                 @Param("statuses") List<PickupStatus> statuses,
                                                 @Param("year") int year,
                                                 @Param("month") int month);

    @Query("SELECT MAX(p.id) AS pickup_id FROM Pickup p")
    int findIdByMax();
}
