package org.team10.washcode.RequestDTO.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.team10.washcode.Enum.UserRole;

@Getter
public class RegisterReqDTO {
    // 사용자 회원가입 (공통)
    @Schema(description = "사용자 ID", example = "ab123@gmail.com")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Schema(description = "비밀번호", example = "ab123")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;

    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    @Schema(description = "주소", example = "서울시 성북구")
    private String address;

    @NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    @Schema(description = "사용자 이름", example = "010-1234-5678")
    private String phone;

    private UserRole role;

    @Schema(description = "카카오 ID")
    private Long kakao_id;
}
