package org.team10.washcode.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.domain.review.entity.Review;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r JOIN r.pickup p " +
            "JOIN p.laundryshop l WHERE l.id = :laundryShopId")
    List<Review> findAllByLaundryShopId(@Param("laundryShopId") int laundryShopId);
}
