package org.team10.washcode.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team10.washcode.domain.laundryshop.entity.LaundryShop;

@Repository
public interface InquiryRepository extends JpaRepository<LaundryShop,Long> {
}
