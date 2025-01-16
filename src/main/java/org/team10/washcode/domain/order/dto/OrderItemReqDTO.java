package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemReqDTO {
    // 주문 세탁물 항목 (고객)
    private int itemId;
    private int quantity;
    private int totalPrice;
}
