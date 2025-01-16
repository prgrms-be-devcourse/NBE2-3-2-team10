package org.team10.washcode.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserProfileResDTO {
    private String name;
    private String email;
    private String address;
    private String phone;
}
