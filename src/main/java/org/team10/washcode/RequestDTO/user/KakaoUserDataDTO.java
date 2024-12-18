package org.team10.washcode.RequestDTO.user;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class KakaoUserDataDTO {
    private Long id;
    private String email;
    private String nickname;

    public KakaoUserDataDTO(String nickname, String email, Long id) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return "KakaoUserDataDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
