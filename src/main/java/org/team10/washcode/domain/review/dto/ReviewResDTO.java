package org.team10.washcode.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResDTO {
    // 리뷰 조회
    private int pickupId;
    private String userName;
    private String content;
}
