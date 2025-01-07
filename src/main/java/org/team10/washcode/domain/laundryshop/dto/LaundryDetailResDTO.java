package org.team10.washcode.domain.laundryshop.dto;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.domain.handledItems.entity.HandledItems;

import java.util.List;
@Setter
@Getter
public class LaundryDetailResDTO {
    // 세탁소 상세 보기
    private String shop_name;
    private String phone;
    private String address;
    private String non_operating_days;
    private String business_number;
    private String user_name;

    private List<HandledItems> handledItems;
}
