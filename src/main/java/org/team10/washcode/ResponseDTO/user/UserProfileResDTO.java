package org.team10.washcode.ResponseDTO.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserProfileResDTO {
    private String name;
    private String address;
    private String phone;
}
