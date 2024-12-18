package org.team10.washcode.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;
import java.util.List;

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

    @Enumerated(EnumType.STRING)//Enum 값을 문자열로 저장
    private PickupStatus status;          //상태

    private String content;         //요청내용
    @CreatedDate
    private Timestamp created_at;   //요청생성  
    private Timestamp update_at;    //요청 갱신
}
