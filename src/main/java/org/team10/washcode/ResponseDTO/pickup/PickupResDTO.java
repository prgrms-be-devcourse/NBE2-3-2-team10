package org.team10.washcode.ResponseDTO.pickup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class PickupResDTO {
    private int pickup_id;
    private String shop_name;
    private PickupStatus status;
    private Timestamp created_at;
    private Timestamp update_at;
    private String content;
}
