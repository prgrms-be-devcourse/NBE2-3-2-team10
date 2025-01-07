package org.team10.washcode.domain.pickup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.global.comm.enums.PickupStatus;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PickupSalesSummeryDTO {
    private int pickupId;
    private PickupStatus status;
    private Timestamp createdAt;
    private String address;

    private List<PickupSalesSummeryDTO.OrderItemDTO> orderItems;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderItemDTO {
        private String itemName;
        private int quantity;
        private int totalPrice;
    }
}
