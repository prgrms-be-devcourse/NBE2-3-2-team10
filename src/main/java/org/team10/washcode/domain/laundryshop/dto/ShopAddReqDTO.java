package org.team10.washcode.domain.laundryshop.dto;

import lombok.Getter;

@Getter
public class ShopAddReqDTO {
    // 세탁소 등록 (업체)
    private String shopName;
    private String businessNumber;
    private String address;
    private String phone;
    private String nonOperatingDays;
    private String userName;

    private double latitude;
    private double longitude;

    private int user_id;

}
