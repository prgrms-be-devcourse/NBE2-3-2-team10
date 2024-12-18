package org.team10.washcode.RequestDTO.user;

import lombok.Getter;

@Getter
public class RegisterReqDTO {
    // 사용자 회원가입 (공통)
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone;
}
