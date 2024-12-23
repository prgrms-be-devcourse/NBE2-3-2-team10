package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.user.UserProfileResDTO;
import org.team10.washcode.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT EXISTS (SELECT true FROM User u where u.email = :email)")
    Boolean findByEmailExists(@Param("email") String email);

    @Query("SELECT EXISTS (SELECT true FROM User u where u.email = :email and u.password = :password)")
    Boolean findByPasswordEquals(@Param("email") String email, @Param("password") String password);

    //@Query("select new org.team10.washcode.ResponseDTO.user.UserProfileResDTO(u.name, u.address, u.phone) from User u where u.id = :id")
    //UserProfileResDTO findUserProfileById(@Param("id") int id);

    Optional<User> findById(@Param("id") int id);
}
