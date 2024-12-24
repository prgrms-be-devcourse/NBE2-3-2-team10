package org.team10.washcode.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.team10.washcode.RequestDTO.user.LoginReqDTO;
import org.team10.washcode.RequestDTO.user.RegisterReqDTO;
import org.team10.washcode.RequestDTO.user.UserUpdateReqDTO;
import org.team10.washcode.ResponseDTO.user.UserProfileResDTO;
import org.team10.washcode.entity.User;
import org.team10.washcode.jwt.JwtProvider;
import org.team10.washcode.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Value("${ACCESS_TOKEN_EXPIRATION_TIME}")
    private int ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${REFRESH_TOKEN_EXPIRATION_TIME}")
    private int REFRESH_TOKEN_EXPIRATION_TIME;

    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private JwtProvider jwtProvider;

    public ResponseCookie getRefreshToken(String refreshToken) {
        return ResponseCookie
                .from("REFRESHTOKEN", refreshToken) // 추후 토큰값 추가
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .maxAge(REFRESH_TOKEN_EXPIRATION_TIME)
                .build();
    }

    public ResponseEntity<?> signup(RegisterReqDTO registerReqDTO){
        try {
            // 이메일 검증
            if(userRepository.findByEmailExists(registerReqDTO.getEmail())){
                return ResponseEntity.status(409).body("이미 사용중인 이메일 입니다.");
            }

            User user = new User();
            user.setEmail(registerReqDTO.getEmail());
            user.setPassword(registerReqDTO.getPassword());
            user.setName(registerReqDTO.getName());
            user.setAddress(registerReqDTO.getAddress());
            user.setPhone(registerReqDTO.getPhone());
            user.setRole(registerReqDTO.getRole());
            user.setKakao_id(registerReqDTO.getKakao_id());

            userRepository.save(user);

            return ResponseEntity.ok().body("회원가입에 성공했습니다.");
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }

    public ResponseEntity<?> login(LoginReqDTO loginReqDTO){
        try {
            // 이메일 검증
            if(!userRepository.findByEmailExists(loginReqDTO.getEmail())){
                return ResponseEntity.status(409).body("계정을 찾을 수 없습니다.");
            }
            // 비밀번호 검증 (추후 암호화 예정)
            if(!userRepository.findByPasswordEquals(loginReqDTO.getEmail(),loginReqDTO.getPassword())){
                return ResponseEntity.status(400).body("잘못된 비밀번호 입니다.");
            }

            User user = userRepository.findByEmail(loginReqDTO.getEmail()).orElseThrow(() -> new RuntimeException("해당 계정을 찾을 수 없습니다."));

            String accessToken = jwtProvider.generateAccessToken(user.getId(),user.getRole());
            String refreshToken = jwtProvider.generateRefreshToken(user.getId(),user.getRole());

            Map<String,String> responseAccessToken = new HashMap<>();
            responseAccessToken.put("accessToken",accessToken);

            ResponseCookie refreshCookie = getRefreshToken(refreshToken);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(responseAccessToken);
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }

    public ResponseEntity<?> getUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다"));

        return ResponseEntity
                .ok()
                .body(new UserProfileResDTO(
                        user.getName(),
                        user.getEmail(),
                        user.getAddress(),
                        user.getPhone()));
    }

    @Transactional
    public ResponseEntity<?> updateUser(int id, UserUpdateReqDTO userUpdateReqDTO){
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다"));
            user.setAddress(userUpdateReqDTO.getAddress());
            user.setPhone(userUpdateReqDTO.getPhone());
            if(userUpdateReqDTO.getPassword()!=null){
                user.setPassword(userUpdateReqDTO.getPassword());
            }

            return ResponseEntity.ok().body("성공적으로 수정 되었습니다.");
        } catch (RuntimeException e) {
            System.out.println("[Error] "+ e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }


    public ResponseEntity<?> deleteUser(int id){
        try {
            userRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok().body("회원 탈퇴가 완료 되었습니다.");
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }

    public ResponseEntity<?> getUserRole(Cookie cookie){
        try {
            return ResponseEntity.ok().body(userRepository.findRoleAndNameById(Integer.parseInt(cookie.getValue())));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("잘못된 토큰 값");
        }
    }

    public ResponseEntity<?> getUserAddress(Cookie cookie){
        try {
            return ResponseEntity.ok().body(userRepository.findAddressById(Integer.parseInt(cookie.getValue())));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("잘못된 토큰 값");
        }
    }

    public ResponseEntity<?> logout() {
        try {
            // Access Token 유효시간을 0으로 설정
            ResponseCookie access_cookie = ResponseCookie
                    .from("ACCESSTOKEN", "") // 추후 토큰값 추가
                    .domain("localhost")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(0)
                    .build();

            // Refresh Token 유효시간을 0으로 설정
            ResponseCookie refresh_cookie = ResponseCookie
                    .from("REFRESHTOKEN", "") // 추후 토큰값 추가
                    .domain("localhost")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(0)
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, access_cookie.toString(), refresh_cookie.toString())
                    .body("Logout SUCCESS");
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("Logout ERROR");
        }
    }

    public ResponseEntity<?> checkLogin(HttpServletRequest request) {

        // Access Token이 쿠키에 있는지 확인
        // 없으면 로그인 안되어 있다고 401후, 에러 반환
        Cookie[] cookies = request.getCookies();
        String accessToken = null;
        String refreshToken = null;
        ResponseCookie accessCookie = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("ACCESSTOKEN")) {
                accessToken = cookie.getValue();
            } else if (cookie.getName().equals("REFRESHTOKEN")) {
                refreshToken = cookie.getValue();
            }
        }

        try {
            // Refresh Token이 일치하지 않으면 에러 반환
            if ( !refreshToken.equals("5678") ) {
                throw new IllegalArgumentException("Refresh Token is not valid");
            }
            accessCookie = getAccessToken(Integer.parseInt(accessToken));

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                    .body("Logged in");
        } catch (IllegalArgumentException e) {
            System.out.println("[Error] " + e.getMessage());
            return ResponseEntity.status(400).body("Bad Request: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("Refresh Token ERROR");
        }
    }
}
