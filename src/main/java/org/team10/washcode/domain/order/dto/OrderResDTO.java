package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderResDTO {
    // 주문 요청 (고객)_상세보기
    private String name;
    private String address;
    private String phone;

    private String shopName;
    private String content;
    private String status;
  
    private String createdAt;   //요청 생성
    private String updateAt;

    //결제 방법
    private int paymentId;
    private String method;
    private int amount;
    private Timestamp payment_datetime;

    private int price;//각 카테고리별 가격
    List<OrderItem> orderItems;

    // OrderItem을 정적 이너 클래스로 정의
    @Setter
    @Getter
    public static class OrderItem {
        private String itemName;
        private int quantity;
        private int totalPrice;


        public OrderItem(String itemName, int quantity, int totalPrice) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }
    }
    public String getStatus() {
        return status != null ? status : null;
    }

    // getOrder_items() 메서드 구현
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}

