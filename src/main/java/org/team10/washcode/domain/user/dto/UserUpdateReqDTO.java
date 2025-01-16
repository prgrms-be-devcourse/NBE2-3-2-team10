package org.team10.washcode.domain.user.dto;

import lombok.Data;

@Data
public class UserUpdateReqDTO {
    // 사용자 정보 수정 (공통)
    private String address;
    private String phone;
    private String password;
}
