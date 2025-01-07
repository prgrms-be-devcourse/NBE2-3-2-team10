package org.team10.washcode.domain.handledItems.dto;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.global.comm.enums.LaundryCategory;

@Getter
@Setter
public class ItemInfoResDTO {

    private int item_id;
    private String item_name;
    private LaundryCategory category;
    private int price;

    public ItemInfoResDTO(int item_id, String item_name, LaundryCategory category, int price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.category = category;
        this.price = price;
    }
}
