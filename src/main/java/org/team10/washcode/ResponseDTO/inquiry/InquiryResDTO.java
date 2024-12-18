package org.team10.washcode.ResponseDTO.inquiry;

import lombok.Setter;

import java.sql.Timestamp;
@Setter
public class InquiryResDTO {
    //문의사항 조회
    private int inquiry_id;
    private String email;
    private String inquiry_content;
    private String reply_content;
    private Timestamp created_at;
}
