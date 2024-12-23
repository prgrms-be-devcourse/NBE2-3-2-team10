package org.team10.washcode.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.team10.washcode.RequestDTO.user.LoginReqDTO;
import org.team10.washcode.RequestDTO.user.RegisterReqDTO;
import org.team10.washcode.RequestDTO.user.UserUpdateReqDTO;
import org.team10.washcode.ResponseDTO.user.UserProfileResDTO;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // 토큰 만료 시간(초)
    private final int ACCESS_TOKEN_EXPIRATION_TIME = 1800;  // 30분
    private final int REFRESH_TOKEN_EXPIRATION_TIME = 432000;  // 5일


    @Autowired
    private UserRepository userRepository;


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

            ResponseCookie access_cookie = ResponseCookie
                    .from("ACCESSTOKEN", "1") // 추후 토큰값 추가
                    .domain("localhost")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(ACCESS_TOKEN_EXPIRATION_TIME)
                    .build();

            ResponseCookie refresh_cookie = ResponseCookie
                    .from("REFRESHTOKEN", "5678") // 추후 토큰값 추가
                    .domain("localhost")
                    .path("/")
                    .httpOnly(true)
                    .maxAge(REFRESH_TOKEN_EXPIRATION_TIME)
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, access_cookie.toString(), refresh_cookie.toString())
                    .body("로그인에 성공했습니다.");
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }

    public ResponseEntity<?> getUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        int id = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("ACCESSTOKEN")) {
                id = Integer.parseInt(cookie.getValue());
            }
        }

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
    public ResponseEntity<?> updateUser(UserUpdateReqDTO userUpdateReqDTO, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Long id = 0L;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("ACCESSTOKEN")) {
                id = Long.parseLong(cookie.getValue());
            }
        }

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


    public ResponseEntity<?> deleteUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("ACCESSTOKEN")) {
                Long id = Long.parseLong(cookie.getValue());
                userRepository.deleteById(id);
                return ResponseEntity.ok().body("회원 탈퇴가 완료 되었습니다.");
            }
        }
        return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
    }
}
