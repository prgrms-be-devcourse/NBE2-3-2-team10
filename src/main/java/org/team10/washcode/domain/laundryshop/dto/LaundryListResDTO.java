package org.team10.washcode.domain.laundryshop.dto;

import lombok.Setter;

@Setter
public class LaundryListResDTO {
    // 카테고리 세탁소 목록
    private String shopName;
    private String address;
    private String nonOperatingDays;
}
