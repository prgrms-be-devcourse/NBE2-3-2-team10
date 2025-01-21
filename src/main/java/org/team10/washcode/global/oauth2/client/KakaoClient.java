package org.team10.washcode.global.oauth2.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.team10.washcode.domain.user.dto.KakaoUserDataDTO;
import org.team10.washcode.domain.user.entity.User;
import org.team10.washcode.global.auth.JwtProvider;
import org.team10.washcode.domain.user.repository.UserRepository;
import org.team10.washcode.domain.user.service.UserService;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoClient {

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
    private final JwtProvider jwtProvider;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
    private static final int PASSWORD_LENGTH = 12;

    public ResponseCookie getRefreshToken(String refreshToken) {
        return ResponseCookie
                .from("REFRESHTOKEN", refreshToken) // 추후 토큰값 추가
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .maxAge(REFRESH_TOKEN_EXPIRATION_TIME)
                .build();
    }

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
    public String login(User user, HttpServletResponse response){
        try {
            String accessToken = jwtProvider.generateAccessToken(user.getId(),user.getRole());
            String refreshToken = jwtProvider.generateRefreshToken(user.getId(),user.getRole());

            Map<String,String> responseAccessToken = new HashMap<>();
            responseAccessToken.put("accessToken",accessToken);

            // 리프레쉬 토큰을 쿠키로 만듬
            ResponseCookie refreshCookie = getRefreshToken(refreshToken);

            // 응답 헤더에 쿠키 추가
            response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

            // 응답 바디에 Access Token 포함
            log.info("[Kakao Login] "+ accessToken);
            return accessToken;
        } catch (Exception e) {
            System.out.println("[Error] "+ e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setHeader("error", e.getMessage());

            return e.getMessage();
        }
    }


    public String kakaoLogin(String code, Model model, HttpServletResponse response) {

        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. "액세스 토큰"으로 "사용자 정보" 요청
        KakaoUserDataDTO result = getKakaoUserData(accessToken);

        // 3. 카카오로 가입되어 있지 확인
        // 3-1 가입되어 있지 않다면 회원가입 페이지로 이동
        Optional<User> user = userRepository.findIdByKakaoId(result.getId());
        if (user.isEmpty()) {
            model.addAttribute("kakaoUserData", result);
            return "glober/register";
        }

        // 3-2 가입이 되어 있으면 쿠키를 통해 토큰 발행 후, 로그인 진행
        model.addAttribute("AccessToken", login(user.get(), response));
        return "glober/kakaoLoginWait";
    }
}
