package org.team10.washcode.ResponseDTO.pickup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PickupResDTO {
    private int pickupId;
    private PickupStatus status;
    private Timestamp createdAt;
    private String address;

    private List<OrderItemDTO> orderItems;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderItemDTO {
        private String itemName;
        private int quantity;
        private int totalPrice;
    }
}
