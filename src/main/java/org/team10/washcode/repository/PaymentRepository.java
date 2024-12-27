package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.order.PaymentDTO;
import org.team10.washcode.entity.Payment;

import java.util.List;
import java.util.Map;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByPickupId(Long pickupId);

    // 결제 내역 조회 (상세보기)
    @Query("SELECT u.name, ls.shop_name, p.id, p.created_at, p.update_at, " +
            "hi.item_name, hi.category, hi.price, pi.quantity, pi.totalPrice, " +
            "pay.payment_datetime, pay.amount, pay.method " +
            "FROM User u " +
            "JOIN Pickup p ON u.id = p.user.id " +
            "JOIN LaundryShop ls ON ls.id = p.laundryshop.id " +
            "LEFT JOIN PickupItem pi ON p.id = pi.pickup.id " +
            "LEFT JOIN HandledItems hi ON pi.handledItems.id = hi.id " +
            "LEFT JOIN Payment pay ON p.id = pay.pickup.id " +
            "WHERE u.id = :userId AND p.id = :pickupId")
    List<PaymentDTO> findPaymentDetail(@Param("userId") int userId, @Param("pickupId") int pickupId);

}
