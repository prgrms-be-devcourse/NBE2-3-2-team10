package org.team10.washcode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.team10.washcode.Enum.UserRole;

import java.sql.Timestamp;

@Entity
@Data
public class User {
    // Customer -> User로 변경
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long kakao_id;
    private String name;
    private String password;
    private String address;
    private String phone;
    private String email;
    private UserRole role;
    private Timestamp created_at;
    
}
