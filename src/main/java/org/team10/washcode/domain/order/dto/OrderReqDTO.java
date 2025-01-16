package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReqDTO {
    // 수거 주문 (고객)
    private int laundryshopId;
    private String content;

    private int itemId;
    private int quantity;
    private String paymentMethod;
}
