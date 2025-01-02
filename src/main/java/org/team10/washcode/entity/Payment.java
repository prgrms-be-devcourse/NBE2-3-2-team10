package org.team10.washcode.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Data
@Entity
public class Payment {
    //결제
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     //결제id

    @OneToOne
    @JoinColumn(name = "pickup_id")
    private Pickup pickup;     //요청 id

    @CreationTimestamp
    private Timestamp payment_datetime; //결제일시
    private int amount;     //결제금액
    private String method; //결제수단
}
