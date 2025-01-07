package org.team10.washcode.domain.order.entity.db;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.team10.washcode.domain.order.dto.KakaoPayApproveRes;
import org.team10.washcode.domain.pickup.entity.Pickup;

import java.sql.Timestamp;

@Data
@Entity
public class Payment {
    //결제
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     //결제id

    @OneToOne
    @JoinColumn(name = "pickup_id")
    private Pickup pickup;     //요청 id

    @CreationTimestamp
    private Timestamp payment_datetime; //결제일시
    private int amount;     //결제금액
    private String method;  //결제수단

    // 카카오페이 등 결제 데이터
    private String aid;                 // 요청 고유 번호
    private String tid;                 // 결제 고유 번호
    private String paymentMethodType;   // 결제 수단, CARD 또는 MONEY 중 하나
    private String createdAt;           // 결제 준비 요청 시각
    private String approvedAt;          // 결제 승인 시각
    private String payload;             // 결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용

    // 간편결제 후, 데이터 삽입
    public void setKakaoPayData(KakaoPayApproveRes kakaoPayApproveRes) {
        this.aid = kakaoPayApproveRes.getAid();
        this.tid = kakaoPayApproveRes.getTid();
        this.paymentMethodType = kakaoPayApproveRes.getPaymentMethodType();
        this.createdAt = kakaoPayApproveRes.getCreatedAt();
        this.approvedAt = kakaoPayApproveRes.getApprovedAt();
        this.payload = kakaoPayApproveRes.getPayload();
    }
}
