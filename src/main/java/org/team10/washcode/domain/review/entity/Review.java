package org.team10.washcode.domain.review.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.team10.washcode.domain.pickup.entity.Pickup;

import java.sql.Timestamp;

@Entity
@Data
public class Review {
    // 리뷰
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;// 리뷰ID(PK)

    @ManyToOne
    @JoinColumn(name = "pickup_id")
    private Pickup pickup;          // 요청ID(FK, Request

    private String content;         // 리뷰내용
    private Timestamp created_at;   // 작성일자




    // 세탁소ID(FK, Laund) -> 중복되어서 삭제
    // 필요하면 pickup_id를 통해 Laund를 찾을 수 있음
    // private int laundryshop_id;
}
