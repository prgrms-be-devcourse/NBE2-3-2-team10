package org.team10.washcode.domain.laundryshop.dto;

import lombok.Getter;

@Getter
public class ShopAddReqDTO {
    // 세탁소 등록 (업체)
    private String shop_name;
    private String business_number;
    private String address;
    private String phone;
    private String non_operating_days;
    private String user_name;

    private double latitude;
    private double longitude;

    private int user_id;

}
