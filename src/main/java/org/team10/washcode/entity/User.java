package org.team10.washcode.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.team10.washcode.Enum.UserRole;

import java.sql.Timestamp;

@Entity
@Data
@DynamicInsert
public class User {
    // Customer -> User로 변경
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private Long kakao_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    @Column(nullable = false)
    private UserRole role;

    @CreationTimestamp
    private Timestamp created_at;
    
}
