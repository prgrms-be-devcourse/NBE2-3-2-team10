package org.team10.washcode.ResponseDTO.laundry;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.Enum.LaundryCategory;
@Setter
@Getter
public class HandledItemsResDTO {
    // 세탁소 판매 항목 및 가격
    private String item_name;
    private LaundryCategory category;
    private int price;
}
