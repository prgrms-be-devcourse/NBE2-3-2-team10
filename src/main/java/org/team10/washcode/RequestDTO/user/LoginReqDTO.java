package org.team10.washcode.RequestDTO.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginReqDTO {
    // 사용자 로그인 (공통)
    @Schema(description = "사용자 ID", example = "custom@test.com")
    private String email;

    @Schema(description = "비밀번호", example = "123")
    private String password;
}
