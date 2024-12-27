package org.team10.washcode.ResponseDTO.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
public class PaymentDTO {
    private String userName;
    private String shopName;
    private int pickupId;
    private Timestamp pickupCreatedAt;
    private Timestamp pickupUpdatedAt;
    private int totalPrice;
    private Timestamp paymentDateTime;
    private int amount;
    private String method;
    List<ItemDetail> itemDetails;

    public PaymentDTO(String userName, String shopName, int pickupId, Timestamp pickupCreatedAt,
                      Timestamp pickupUpdatedAt, int totalPrice, Timestamp paymentDateTime, int amount, String method, List<ItemDetail> itemDetails) {
        this.userName = userName;
        this.shopName = shopName;
        this.pickupId = pickupId;
        this.pickupCreatedAt = pickupCreatedAt;
        this.pickupUpdatedAt = pickupUpdatedAt;
        this.totalPrice = totalPrice;
        this.paymentDateTime = paymentDateTime;
        this.amount = amount;
        this.method = method;
        this.itemDetails = itemDetails;
    }

    @Setter
    public static class ItemDetail {
        private String itemName;
        private String category;
        private int price;
        private int quantity;

        public ItemDetail(String itemName, String category, int price, int quantity) {
            this.itemName = itemName;
            this.category = category != null ? category.toString() : null;  // Enum을 String으로 변환
            this.price = price;
            this.quantity = quantity;
        }
    }

}
