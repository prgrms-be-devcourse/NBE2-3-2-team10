package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderlistResDTO {
    // 주문 내역 조회 (전체) (고객)
    private int pickupId;
    private String shopName;
    private String status;
    private String createdAt;   //요청 생성
    private String updateAt;    //요청 갱신

    public OrderlistResDTO(int pickupId, String shop_name, String status, String created_at) {
        this.pickupId = pickupId;
        this.shopName = shop_name;
        this.status = status;
        this.createdAt = created_at;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
