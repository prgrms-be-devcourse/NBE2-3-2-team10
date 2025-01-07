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

    private String shop_name;
    private String content;
    private String status;
  
    private String created_at;   //요청 생성
    private String update_at;

    //결제 방법
    private int paymentId;
    private String method;
    private int amount;
    private Timestamp payment_datetime;

    private int price;//각 카테고리별 가격
    List<OrderItem> order_items;

    // OrderItem을 정적 이너 클래스로 정의
    @Setter
    @Getter
    public static class OrderItem {
        private String item_name;
        private int quantity;
        private int totalPrice;


        public OrderItem(String item_name, int quantity, int totalPrice) {
            this.item_name = item_name;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }
    }
    public String getStatus() {
        return status != null ? status : null;
    }

    // getOrder_items() 메서드 구현
    public List<OrderItem> getOrder_items() {
        return order_items;
    }
}

