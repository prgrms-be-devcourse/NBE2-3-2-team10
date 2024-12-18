package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select count(*) from User u where u.email = :email")
    int findByEmailExists(@Param("email") String email);

}
