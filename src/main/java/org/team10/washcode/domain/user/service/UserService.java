package org.team10.washcode.domain.user.service;



import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.team10.washcode.global.comm.enums.UserRole;
import org.team10.washcode.domain.user.dto.LoginReqDTO;
import org.team10.washcode.domain.user.dto.RegisterReqDTO;
import org.team10.washcode.domain.user.dto.UserUpdateReqDTO;
import org.team10.washcode.domain.user.dto.UserProfileResDTO;
import org.team10.washcode.domain.user.entity.User;
import org.team10.washcode.domain.order.entity.redis.Token;
import org.team10.washcode.global.auth.JwtProvider;
import org.team10.washcode.domain.user.repository.UserRepository;
import org.team10.washcode.domain.order.repository.redis.TokenRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${REFRESH_TOKEN_EXPIRATION_TIME}")
    private int REFRESH_TOKEN_EXPIRATION_TIME;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public ResponseCookie getRefreshToken(String refreshToken) {
        return ResponseCookie
                .from("REFRESHTOKEN", refreshToken) // 추후 토큰값 추가
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .maxAge(REFRESH_TOKEN_EXPIRATION_TIME)
                .build();
    }

    public ResponseEntity<?> checkEmailDuplication (String email) {
        try {
            if (userRepository.findByEmailExists(email)) {
                return ResponseEntity.status(409).body("이미 사용중인 이메일 입니다.");
            } else {
                return ResponseEntity.ok().body("사용 가능한 이메일 입니다.");
            }
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }

    public ResponseEntity<?> signup(RegisterReqDTO registerReqDTO){
        try {
            // 이메일 검증
            if(userRepository.findByEmailExists(registerReqDTO.getEmail())){
                return ResponseEntity.status(409).body("이미 사용중인 이메일 입니다.");
            }

            String encodePassword = passwordEncoder.encode(registerReqDTO.getPassword());

            User user = new User();
            user.setEmail(registerReqDTO.getEmail());
            user.setPassword(encodePassword);
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
            if(!passwordEncoder.matches(loginReqDTO.getPassword(), userRepository.findByPassword(loginReqDTO.getEmail()))){
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
            user.setPassword(userUpdateReqDTO.getPassword());

            System.out.println(userUpdateReqDTO.getPassword() + " " + userUpdateReqDTO.getAddress() + " " + userUpdateReqDTO.getPhone());

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

    public ResponseEntity<?> getUserRole(int id){
        try {
            return ResponseEntity.ok().body(userRepository.findRoleAndNameById(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("잘못된 토큰 값");
        }
    }

    public ResponseEntity<?> getUserAddress(int id){
        try {
            return ResponseEntity.ok().body(userRepository.findAddressById(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("잘못된 토큰 값");
        }
    }

    public ResponseEntity<?> logout() {
        try {
            // Access Token 은 Front 에서 삭제 필요
            // Refresh Token 유효시간을 0으로 설정
            ResponseCookie refreshCookie = ResponseCookie
                    .from("REFRESHTOKEN", "") // 추후 토큰값 추가
                    .domain("localhost")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(0)
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body("Logout SUCCESS");
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("Logout ERROR");
        }
    }

    // 토큰 유효성 검사 및 토큰 재발급
    public ResponseEntity<?> checkLogin(HttpServletRequest request) {
        // 토큰 가져오기
        String accessToken = jwtProvider.resolveAccessToken(request);
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("REFRESHTOKEN")) {
                refreshToken = cookie.getValue();
            }
        }

        User user = null;

        try {
            // AccessToken 유효성 확인
            if (accessToken != null && jwtProvider.validateToken(accessToken)){
                return ResponseEntity.ok().body("accessToken 정상");
                // RefreshToken 유효성 확인 후 AccessToken 재발급
            } else if (refreshToken != null && !jwtProvider.validateToken(accessToken) && jwtProvider.validateToken(refreshToken)){
                // RefreshToken 유효성 확인 후, UserId 가져오기
                int userId = jwtProvider.getId(refreshToken);

                // Redis에서 RefreshToken 가져오기
                // 없으면 람다로 에러 발생
                Token token = tokenRepository.findById(userId).orElseThrow(() -> new RuntimeException(userId + "번 유저 Redis에 RefreshToken이 없습니다."));

                if (token.getToken().equals(refreshToken)) {
                    UserRole role = jwtProvider.getRole(refreshToken);

                    // RefreshToken 재발급
                    // RTR 방식 (Refresh Token Rotation) -> AccessToken 발급 시, 새로운 RefreshToken도 같이 발급
                    refreshToken = jwtProvider.generateRefreshToken(userId, role);
                    ResponseCookie refreshCookie = getRefreshToken(refreshToken);

                    // AccessToken 반환
                    String newAccessToken = jwtProvider.generateAccessToken(userId, role);
                    Map<String,String> responseAccessToken = new HashMap<>();
                    responseAccessToken.put("accessToken", newAccessToken);

                    return ResponseEntity
                            .ok()
                            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                            .body(responseAccessToken);
                } else {
                    throw new IllegalArgumentException(userId + "번 유저 Redis에 있는 RefreshToken과 다름, 탈취 위험있음");
                }
            // 모든 토큰 유효성 검사 실패
            } else {
                return ResponseEntity.status(400).body("token Expired");
            }
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("Refresh Token ERROR");
        }
    }
  
    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found"));
    }
}
