package org.team10.washcode.ResponseDTO.user;

import lombok.Data;
import org.team10.washcode.Enum.UserRole;

@Data
public class UserMyPageResDTO {
    private UserRole role;
    private String name;

    public UserMyPageResDTO(UserRole role, String name) {
        this.role = role;
        this.name = name;
    }
}
