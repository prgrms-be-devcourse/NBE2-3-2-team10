package org.team10.washcode.RequestDTO.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginReqDTO {
    private Long id;
    private String nickname;
    private String email;
    //private AuthTokens token;
}
