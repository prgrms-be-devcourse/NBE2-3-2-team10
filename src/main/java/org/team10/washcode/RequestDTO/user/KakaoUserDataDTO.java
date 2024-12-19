package org.team10.washcode.RequestDTO.user;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class KakaoUserDataDTO {
    private Long id;
    private String email;
    private String nickname;
    private String password;

    public KakaoUserDataDTO(String nickname, String email, Long id, String pw) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
        this.password = pw;
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
