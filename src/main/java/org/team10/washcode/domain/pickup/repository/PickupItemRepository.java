package org.team10.washcode.domain.pickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.domain.pickup.entity.PickupItem;

import java.util.List;

@Repository
public interface PickupItemRepository extends JpaRepository<PickupItem,Long> {
    List<PickupItem> findByPickupId(Long pickupId);
}
