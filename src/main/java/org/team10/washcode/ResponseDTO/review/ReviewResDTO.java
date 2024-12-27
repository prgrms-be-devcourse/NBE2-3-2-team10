package org.team10.washcode.ResponseDTO.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResDTO {
    // 리뷰 조회
    private int pickup_id;
    private String user_name;
    private String content;
}
