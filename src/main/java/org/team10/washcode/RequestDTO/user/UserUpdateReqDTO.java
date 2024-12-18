package org.team10.washcode.RequestDTO.user;

import lombok.Getter;

@Getter
public class UserUpdateReqDTO {
    // 사용자 정보 수정 (공통)
    private String name;
    private String address;
    private String phone;
    private String password;
}
