package org.team10.washcode.RequestDTO.laundry;

import lombok.Getter;

@Getter
public class ShopAddReqDTO {
    // 세탁소 등록 (업체)
    private String shop_name;
    private String business_number;
    private String address;
    private String phone;
    private String non_operating_days;

    private double latitude;
    private double longitude;

    private int user_id;

}
