package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoPayReqDTO {
    private String name;
    private int totalPrice;
    private int quantity;
    private int paymentId;
}
