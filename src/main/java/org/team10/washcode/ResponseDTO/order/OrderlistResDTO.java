package org.team10.washcode.ResponseDTO.order;

import lombok.Setter;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;

@Setter
public class OrderlistResDTO {
    // 주문 내역 조회 (전체) (고객)
    int pickup_id;
    int shop_name;
    PickupStatus status;
    Timestamp created_at;   //요청 생성
    Timestamp update_at;    //요청 갱신
}
