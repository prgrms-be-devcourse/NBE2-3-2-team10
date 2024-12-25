package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.HandledItems;
import org.team10.washcode.entity.LaundryShop;

@Repository
public interface HandledItemsRepository extends JpaRepository<HandledItems, Long> {
}
