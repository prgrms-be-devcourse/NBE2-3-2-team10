package org.team10.washcode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.team10.washcode.RequestDTO.user.KakaoUserDataDTO;
import org.team10.washcode.repository.UserRepository;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoService {

    @Value("${kakao.key.client-id}")
    private String kakaoApiKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${ACCESS_TOKEN_EXPIRATION_TIME}")
    private int ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${REFRESH_TOKEN_EXPIRATION_TIME}")
    private int REFRESH_TOKEN_EXPIRATION_TIME;

    private final UserRepository userRepository;
    private final UserService userService;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
    private static final int PASSWORD_LENGTH = 12;

    public String getAccessToken(String code) {

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoApiKey);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        // HTTP 요청 (https://kauth.kakao.com/oauth/token 으로)
        HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> res = null;
        try {
            res = rt.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenReq,
                    String.class
            );
        } catch (HttpClientErrorException e) {
            System.out.println("[kakao Login HTTP API 오류] " + e.getMessage());
            return "Token Error";
        }

        // HTTP 응답 (JSON) -> Access Token 파씽
        String resBody = res.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(resBody);
        } catch (JsonProcessingException e) {
            System.out.println("[json 파싱 오류] " + e.getMessage());
        }

        String accessToken = jsonNode.get("access_token").asText();
        String refreshToken = jsonNode.get("refresh_token").asText();

        // Access Token 반환
        return jsonNode.get("access_token").asText();
    }

    public KakaoUserDataDTO getKakaoUserData (String accessToken) {

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> res = null;
        try {
            res = rt.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    kakaoUserInfoRequest,
                    String.class
            );
        } catch (HttpClientErrorException e){
            System.out.println("[kakao Data Access API 오류] " + e.getMessage());
            return null;
        }

        String resBody = res.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(resBody);
        } catch (JsonProcessingException e) {
            System.out.println("[json 파싱 오류] " + e.getMessage());
        }

        // 필요한 값 json에서 파싱
        long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.path("properties").path("nickname").asText();
        String email = jsonNode.path("kakao_account").path("email").asText();

        // DTO에 투입
        // 카카오 유저는 비밀번호가 없어서 랜덤한 비밀번호 값으로 들어감.
        KakaoUserDataDTO kakaoUserDataDTO = new KakaoUserDataDTO(nickname, email ,id, getRandomPassword());

        return kakaoUserDataDTO;
    }

    // 랜덤 비밀번호 생성
    // 나중에 UUID로 전환 예정
    public static String getRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }

    // 카카오를 통해 JTW 토큰을 발행하는 코드
    public void login(Integer userId, HttpServletResponse response){
        try {
            // 액세스 토큰 쿠키 생성
            Cookie accessCookie = new Cookie("ACCESSTOKEN", userId.toString()); // 추후 토큰값 추가
            accessCookie.setDomain("localhost");
            accessCookie.setPath("/");
            accessCookie.setHttpOnly(true);
            accessCookie.setMaxAge(ACCESS_TOKEN_EXPIRATION_TIME); // 만료 시간 설정

            // 리프레시 토큰 쿠키 생성
            Cookie refreshCookie = new Cookie("REFRESHTOKEN", "K5678"); // 추후 토큰값 추가
            refreshCookie.setDomain("localhost");
            refreshCookie.setPath("/");
            refreshCookie.setHttpOnly(true);
            refreshCookie.setMaxAge(REFRESH_TOKEN_EXPIRATION_TIME); // 만료 시간 설정

            // 쿠키를 응답에 추가
            response.addCookie(accessCookie);
            response.addCookie(refreshCookie);

        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
        }
    }


    public String kakaoLogin(String code, Model model, HttpServletResponse response) {

        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. "액세스 토큰"으로 "사용자 정보" 요청
        KakaoUserDataDTO result = getKakaoUserData(accessToken);

        // 3. 카카오로 가입되어 있지 확인
        // 3-1 가입되어 있지 않다면 회원가입 페이지로 이동
        Optional<Integer> userId = userRepository.findIdByKakaoId(result.getId());
        if (userId.isEmpty()) {
            model.addAttribute("kakaoUserData", result);
            return "Glober/register";
        }

        // 3-2 가입이 되어 있으면 쿠키를 통해 토큰 발행 후, 로그인 진행
        login(userId.get(), response);
        return "Customer/main";
    }
}
