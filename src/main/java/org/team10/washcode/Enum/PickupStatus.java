package org.team10.washcode.Enum;

import lombok.Getter;

@Getter
public enum PickupStatus {
    NONE("None"),
    REQUESTED("픽업 신청"),
    PICKED_UP("픽업 완료"),
    PAYMENT_PENDING("결제 대기"),
    PAYMENT_COMPLETED("결제 완료"),
    IN_PROGRESS("작업 중"),
    OUT_FOR_DELIVERY("배송 중"),
    CANCELLED_WITH_DELIVERY_FEE("주문 취소 (배송료는 결제)"),
    CANCELLED("주문 취소"),
    DELIVERED("도착 완료");

    private final String desc;

    // 생성자
    PickupStatus(String description) {
        this.desc = description;
    }

}
