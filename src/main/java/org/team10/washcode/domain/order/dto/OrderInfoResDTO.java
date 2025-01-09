package org.team10.washcode.domain.order.dto;

import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.domain.handledItems.dto.ItemInfoResDTO;
import java.util.List;

@Setter
@Getter
public class OrderInfoResDTO {
    private String name;
    private String address;
    private String shopName;
    private List<ItemInfoResDTO> category;
}

