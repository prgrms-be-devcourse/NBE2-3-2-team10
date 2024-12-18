package org.team10.washcode.ResponseDTO.user;

import lombok.Setter;

@Setter
public class UserResDTO {
    // 회원 정보 요청시 ->상세보기 페이지
    private String name;
    private String address;
    private String phone;
}
