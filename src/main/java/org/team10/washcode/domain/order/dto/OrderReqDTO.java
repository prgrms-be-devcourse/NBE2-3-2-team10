package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReqDTO {
    // 수거 주문 (고객)
    private int laundryshop_id;
    private String content;

    private int item_id;
    private int quantity;
    private String paymentMethod;
}
