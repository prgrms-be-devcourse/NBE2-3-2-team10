package org.team10.washcode.ResponseDTO.pickup;

import lombok.Setter;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;

@Setter
public class PickupResDTO {
    // 수거 요청 목록 조회 (업체)
    private int pickup_id;
    private String shop_name;
    PickupStatus status;
    Timestamp created_at;   //요청 생성
    Timestamp update_at;
}
