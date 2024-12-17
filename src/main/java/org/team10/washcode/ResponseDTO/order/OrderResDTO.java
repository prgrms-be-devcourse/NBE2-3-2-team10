package org.team10.washcode.ResponseDTO.order;

import lombok.Setter;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;
import java.util.List;

@Setter
public class OrderResDTO {
    // 주문 요청 (고객)
    String name;
    String address;
    String phone;
    String content;
    PickupStatus status;
    Timestamp created_at;   //요청 생성
    Timestamp update_at;

    List<OrderItem> order_items;

    // OrderItem을 정적 이너 클래스로 정의
    @Setter
    public static class OrderItem {
        String item_name;
        int quantity;
        int total_price;

        public OrderItem(String item_name, int quantity, int total_price) {
            this.item_name = item_name;
            this.quantity = quantity;
            this.total_price = total_price;
        }
    }
}

