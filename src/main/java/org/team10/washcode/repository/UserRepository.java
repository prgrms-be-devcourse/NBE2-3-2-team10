package org.team10.washcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team10.washcode.ResponseDTO.user.UserMyPageResDTO;
import org.team10.washcode.ResponseDTO.user.UserProfileResDTO;
import org.team10.washcode.entity.User;
import java.util.List;
import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findById(int id);

    @Query("SELECT EXISTS (SELECT true FROM User u where u.email = :email)")
    Boolean findByEmailExists(@Param("email") String email);

    @Query("SELECT EXISTS (SELECT true FROM User u where u.email = :email and u.password = :password)")
    Boolean findByPasswordEquals(@Param("email") String email, @Param("password") String password);

    //@Query("select new org.team10.washcode.ResponseDTO.user.UserProfileResDTO(u.name, u.address, u.phone) from User u where u.id = :id")
    //UserProfileResDTO findUserProfileById(@Param("id") int id);

    @Query("SELECT U.id FROM User U WHERE U.kakao_id = :kakao_id")
    Optional<Integer> findIdByKakaoId(@Param("kakao_id") Long kakao_id);

    // 테스트 코드 -> 이메일로 유저 ID 찾기
    @Query("SELECT U.id FROM User U WHERE U.email = :email")
    Optional<Integer> findIdByEmail(@Param("email") String email);

    @Query("SELECT new org.team10.washcode.ResponseDTO.user.UserMyPageResDTO(U.role, U.name) FROM User U WHERE U.id = :id")
    Optional<UserMyPageResDTO> findRoleAndNameById(int id);

    @Query("SELECT U.address FROM User U WHERE U.id = :id")
    Optional<String> findAddressById(int id);

    Optional<User> findByEmail(String email);

    User findByName(String name);
}
