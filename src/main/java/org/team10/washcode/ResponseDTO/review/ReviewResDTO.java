package org.team10.washcode.ResponseDTO.review;

import lombok.Setter;

@Setter
public class ReviewResDTO {
    // 리뷰 조회
    private int pickup_id;
    private int laundryshop_id;
    private String content;
}
