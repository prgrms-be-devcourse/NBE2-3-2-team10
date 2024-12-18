package org.team10.washcode.RequestDTO.user;

import lombok.Getter;

@Getter
public class LoginReqDTO {
    // 사용자 로그인 (공통)
    private String email;
    private String password;
}
