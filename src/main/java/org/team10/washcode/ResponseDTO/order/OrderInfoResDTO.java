package org.team10.washcode.ResponseDTO.order;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.ResponseDTO.laundry.ItemInfoResDTO;
import java.util.List;

@Setter
@Getter
public class OrderInfoResDTO {
    private String name;
    private String address;
    private String shop_name;
    private List<ItemInfoResDTO> category;
}