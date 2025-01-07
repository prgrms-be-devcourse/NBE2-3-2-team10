package org.team10.washcode.domain.handledItems.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.team10.washcode.global.comm.enums.LaundryCategory;
@Setter
@Getter
@ToString
public class HandledItemsResDTO {
    // 세탁소 판매 항목 및 가격
    private String item_name;
    private LaundryCategory category;
    private int price;

    private int laundry_id;
}
