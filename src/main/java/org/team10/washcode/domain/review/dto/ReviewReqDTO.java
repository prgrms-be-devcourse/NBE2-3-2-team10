package org.team10.washcode.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewReqDTO {
    // 리뷰 등록 (고객)
    private int pickup_id;
    private int laundryshop_id;
    private String content;
}
