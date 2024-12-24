package org.team10.washcode.ResponseDTO.pickup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PickupDeliveryResDTO {
    private int pickupId;                  // 픽업 ID
    private String shopName;               // 세탁소 이름
    private Timestamp createdAt;           // 요청 일자
    private String address;                // 배달 주소
    private String phone;                  // 전화번호
    private String content;                // 요청 내용

    private List<OrderItemDTO> orderItems; // 주문 항목 리스트 (여러 개의 아이템)

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderItemDTO {
        private String itemName;           // 아이템 이름 (예: 와이셔츠)
        private int quantity;              // 수량
        private int totalPrice;            // 총 가격
    }
}
