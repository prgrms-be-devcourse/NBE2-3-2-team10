package org.team10.washcode.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.LaundryShop;

@Repository
public interface InquiryRepository extends JpaRepository<LaundryShop,Long> {
}
