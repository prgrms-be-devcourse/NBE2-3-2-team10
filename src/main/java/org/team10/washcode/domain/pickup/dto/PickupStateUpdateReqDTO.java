package org.team10.washcode.domain.pickup.dto;

import lombok.Getter;

@Getter
public class PickupStateUpdateReqDTO {
    // 주문 상태 변경
    private int pickupId;
    private String status;
}
