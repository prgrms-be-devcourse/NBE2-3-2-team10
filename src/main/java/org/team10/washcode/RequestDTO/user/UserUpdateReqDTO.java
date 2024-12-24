package org.team10.washcode.RequestDTO.user;

import lombok.Data;
import lombok.Getter;

@Data
public class UserUpdateReqDTO {
    // 사용자 정보 수정 (공통)
    private String address;
    private String phone;
    private String password;
}
