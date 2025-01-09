package org.team10.washcode.domain.handledItems.dto;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.global.comm.enums.LaundryCategory;

@Getter
@Setter
public class ItemInfoResDTO {

    private int itemId;
    private String itemName;
    private LaundryCategory category;
    private int price;

    public ItemInfoResDTO(int itemId, String itemName, LaundryCategory category, int price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
    }
}
