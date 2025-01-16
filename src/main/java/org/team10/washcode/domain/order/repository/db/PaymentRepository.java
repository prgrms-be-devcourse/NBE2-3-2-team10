package org.team10.washcode.domain.order.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.domain.order.entity.db.Payment;
import org.team10.washcode.domain.pickup.entity.Pickup;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByPickupId(Long pickupId);
    Optional<Payment> findById(int id);

    @Query("SELECT p.pickup FROM Payment p WHERE p.id = :id")
    Optional<Pickup> findPickUpById(@Param("id") int id);
}
