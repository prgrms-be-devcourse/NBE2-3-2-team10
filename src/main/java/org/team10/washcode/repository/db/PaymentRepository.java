package org.team10.washcode.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByPickupId(Long pickupId);
}
