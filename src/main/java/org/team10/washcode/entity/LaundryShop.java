package org.team10.washcode.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private String phone;
    private String address;
    private String operating_days;
    private String business_hours;

    private double latitude;
    private double longitude;

    private Timestamp created_at;
}
