package org.team10.washcode.domain.pickup.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.team10.washcode.global.comm.enums.PickupStatus;
import org.team10.washcode.domain.laundryshop.entity.LaundryShop;
import org.team10.washcode.domain.user.entity.User;

import java.sql.Timestamp;

@Data
@Entity
public class Pickup {
    // 빨래수거요청 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;            //요청

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;        //고객

    @ManyToOne
    @JoinColumn(name = "laundryshop_id")
    private LaundryShop laundryshop;     //세탁

    @Enumerated(EnumType.STRING)
    private PickupStatus status;          //상태

    private String content;         //요청내용
    @CreationTimestamp
    private Timestamp created_at;   //요청생성
    @UpdateTimestamp
    private Timestamp update_at;    //요청 갱신
}
