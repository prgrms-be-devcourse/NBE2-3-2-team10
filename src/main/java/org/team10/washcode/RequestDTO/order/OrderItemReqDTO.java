package org.team10.washcode.RequestDTO.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemReqDTO {
    // 주문 세탁물 항목 (고객)
    private int item_id;
    private int quantity;
    private int Totalprice;
}
