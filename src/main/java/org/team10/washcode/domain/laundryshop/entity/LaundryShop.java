package org.team10.washcode.domain.laundryshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.team10.washcode.domain.user.entity.User;

import java.sql.Timestamp;

@Data
@Entity
public class LaundryShop {
    //세탁소
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // 외래 키 설정
    private User user;

    private String shop_name;
    private String business_number;
    private String user_name;
    private String phone;
    private String address;
    private String non_operating_days;

    private Double latitude;
    private Double longitude;

    @CreationTimestamp
    private Timestamp created_at;
}
