package org.team10.washcode.domain.laundryshop.dto;

import lombok.Setter;

@Setter
public class LaundryListResDTO {
    // 카테고리 세탁소 목록
    private String shop_name;
    private String address;
    private String non_operating_days;
}
