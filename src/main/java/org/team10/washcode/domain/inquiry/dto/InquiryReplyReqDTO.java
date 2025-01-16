package org.team10.washcode.domain.inquiry.dto;
import lombok.Getter;

@Getter
public class InquiryReplyReqDTO {
    // 문의 사항 답변
    private int inquiry_id;
    private String reply_content;
}
