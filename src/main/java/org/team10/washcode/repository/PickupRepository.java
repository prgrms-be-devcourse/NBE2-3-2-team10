package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.Pickup;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {
}
