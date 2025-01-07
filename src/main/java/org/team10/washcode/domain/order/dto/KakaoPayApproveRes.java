package org.team10.washcode.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoPayApproveRes {
    private String aid;                 // 요청 고유 번호
    private String tid;                 // 결제 고유 번호

    @JsonProperty("payment_method_type")
    private String paymentMethodType;   // 결제 수단, CARD 또는 MONEY 중 하나

    @JsonProperty("created_at")
    private String createdAt;           // 결제 준비 요청 시각

    @JsonProperty("approved_at")
    private String approvedAt;          // 결제 승인 시각

    private String payload;             // 결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
}