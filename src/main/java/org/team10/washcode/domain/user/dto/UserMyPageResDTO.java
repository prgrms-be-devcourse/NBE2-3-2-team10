package org.team10.washcode.domain.user.dto;

import lombok.Data;
import org.team10.washcode.global.comm.enums.UserRole;

@Data
public class UserMyPageResDTO {
    private UserRole role;
    private String name;

    public UserMyPageResDTO(UserRole role, String name) {
        this.role = role;
        this.name = name;
    }
}
