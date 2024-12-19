package org.team10.washcode.ResponseDTO.laundry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ShopAddResDTO {
    // 세탁소 등록 (업체)
    private String shop_name;
    private String business_number;
    private String address;
    private String phone;
    private String operating_days;
    private String business_hours;

    private double latitude;
    private double longitude;

    private String user_name;
}
