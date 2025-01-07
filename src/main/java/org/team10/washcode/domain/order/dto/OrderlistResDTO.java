package org.team10.washcode.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
    private String status;
    @JsonProperty("created_at")
    private String created_at;   //요청 생성
    @JsonProperty("updated_at")
    private String update_at;    //요청 갱신

    public OrderlistResDTO(int pickup_id, String shop_name, String status, String created_at) {
        this.pickup_id = pickup_id;
        this.shop_name = shop_name;
        this.status = status;
        this.created_at = created_at;
    }

    public String getShopName() {
        return shop_name;
    }
    public String getCreatedAt() {
        return created_at;
    }
}
