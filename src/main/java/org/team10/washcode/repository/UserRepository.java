package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.user.UserProfileResDTO;
import org.team10.washcode.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select count(*) from User u where u.email = :email")
    int findByEmailExists(@Param("email") String email);

    @Query("select count(*) from User u where u.email = :email and u.password = :password")
    int findByPasswordEquals(@Param("email") String email, @Param("password") String password);

    @Query("select new org.team10.washcode.ResponseDTO.user.UserProfileResDTO(u.name, u.address, u.phone) from User u where u.id = :id")
    UserProfileResDTO findUserProfileById(@Param("id") int id);
}
