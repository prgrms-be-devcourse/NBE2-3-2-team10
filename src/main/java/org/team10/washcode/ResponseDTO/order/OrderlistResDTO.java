package org.team10.washcode.ResponseDTO.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.team10.washcode.Enum.PickupStatus;

import java.sql.Timestamp;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) //null값 제외
public class OrderlistResDTO {
    // 주문 내역 조회 (전체) (고객)
    @JsonProperty("pickup_id")
    private int pickup_id;
    @JsonProperty("shop_name")
    private String shop_name;
    @JsonProperty("status")
    private PickupStatus status;
    @JsonProperty("created_at")
    private Timestamp created_at;   //요청 생성
    @JsonProperty("updated_at")
    private Timestamp update_at;    //요청 갱신

    public OrderlistResDTO(int pickup_id, String shop_name, PickupStatus status, Timestamp created_at) {
        this.pickup_id = pickup_id;
        this.shop_name = shop_name;
        this.status = status;
        this.created_at = created_at;
    }

    public String getShopName() {
        return shop_name;
    }
    public Timestamp getCreatedAt() {
        return created_at;
    }
}
