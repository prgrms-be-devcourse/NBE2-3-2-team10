package org.team10.washcode.RequestDTO.pickup;

import lombok.Getter;

@Getter
public class PickupStateUpdateReqDTO {
    // 주문 상태 변경
    private int pickup_id;
    private String status;
}
