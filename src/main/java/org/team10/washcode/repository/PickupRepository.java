package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.entity.Pickup;

import java.util.List;
import java.util.Optional;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {

    @Query("SELECT DISTINCT p FROM Pickup p " +
            "JOIN FETCH p.user u " +
            "JOIN FETCH p.laundryshop l " +
            "WHERE p.id = :pickupId")
    Optional<Pickup> findPickupWithFetchJoin(@Param("pickupId") long pickupId);

    @Query("SELECT DISTINCT p FROM Pickup p " +
            "JOIN FETCH p.user u " +
            "JOIN FETCH p.laundryshop l " +
            "WHERE p.user.id = :userId " +
            "AND p.status = :status")
    List<Pickup> findAllByUserIdWithFetchJoinAndStatus(@Param("userId") long userId,
                                                       @Param("status") PickupStatus status);
}
