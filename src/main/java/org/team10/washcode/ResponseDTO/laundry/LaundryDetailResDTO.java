package org.team10.washcode.ResponseDTO.laundry;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.entity.HandledItems;
import org.team10.washcode.entity.User;

import java.util.List;
@Setter
@Getter
public class LaundryDetailResDTO {
    // 세탁소 상세 보기
    private String shop_name;
    private String phone;
    private String address;
    private String non_operating_days;

    private List<HandledItemsResDTO> handledItems;

}
