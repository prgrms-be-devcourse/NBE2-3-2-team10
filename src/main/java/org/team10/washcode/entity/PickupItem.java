package org.team10.washcode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PickupItem {
    // 수거 빨랫감 리스트
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;           // 요청 아이템 ID(PK, Request)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_id")  // Pickup 테이블과 1:N (여긴 N 관계) & 양방향 매핑
    private Pickup pickup;

    @ManyToOne
    @JoinColumn(name = "handled_items_id")
    private HandledItems handledItems;  // 카테고리ID(FK, ItemCategory)

    private int quantity;     // 수량

    @JoinColumn(name="total_price")
    private int totalPrice;   // 총가격 Totalprice -> totalPrice


}
