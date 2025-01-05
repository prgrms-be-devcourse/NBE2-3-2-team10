package org.team10.washcode.RequestDTO.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoPayReqDTO {
    private String name;
    private int totalPrice;
    private int quantity;
    private int paymentId;
}
