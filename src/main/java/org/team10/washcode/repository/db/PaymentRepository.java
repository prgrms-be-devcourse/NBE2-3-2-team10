package org.team10.washcode.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.Payment;
import org.team10.washcode.entity.Pickup;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByPickupId(Long pickupId);
    Optional<Payment> findById(int id);

    @Query("SELECT p.pickup FROM Payment p WHERE p.id = :id")
    Optional<Pickup> findPickUpById(@Param("id") int id);
}
