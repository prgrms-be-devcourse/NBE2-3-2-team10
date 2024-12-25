package org.team10.washcode.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.user.LoginReqDTO;
import org.team10.washcode.RequestDTO.user.RegisterReqDTO;
import org.team10.washcode.RequestDTO.user.UserUpdateReqDTO;
import org.team10.washcode.service.UserService;

@RestController
@Tag(name = "User API", description = "유저 인증 관련 API")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입 API 입니다.")
    public ResponseEntity<?> signup(@RequestBody @Valid RegisterReqDTO registerReqDTO){
        return userService.signup(registerReqDTO);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 API 입니다.")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDTO loginReqDTO){
        return userService.login(loginReqDTO);
    }

    // 토큰 추가 후 토큰에 있는 user_id를 매개변수로 변경
    @GetMapping
    @Operation(summary = "회원정보 조회", description = "회원정보를 조회하는 API 입니다.")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal int id){
        return userService.getUser(id);
    }

    @PutMapping
    @Operation(summary = "회원정보 수정", description = "회원정보를 수정하는 API 입니다.")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal int id, @RequestBody UserUpdateReqDTO userUpdateReqDTO){

        return userService.updateUser(id, userUpdateReqDTO);
    }

    @DeleteMapping
    @Operation(summary = "회원 탈퇴", description = "회원정보를 삭제하는 API 입니다.")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal int id){
        return userService.deleteUser(id);
    }

    @GetMapping("/role")
    @Operation(summary = "회원등급 조회", description = "회원의 등급만 조회하는 API 입니다")
    public ResponseEntity<?> getUserRole(@AuthenticationPrincipal int id){
        return userService.getUserRole(id);
    }

    @GetMapping("/address")
    @Operation(summary = "회원주소 조회", description = "회원의 주소만 조회하는 API 입니다")
    public ResponseEntity<?> getUserAddress(@AuthenticationPrincipal int id){
        return userService.getUserAddress(id);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃 API 입니다.")
    public ResponseEntity<?> logout(){
        return userService.logout();
    }

    @PostMapping("/check-login")
    @Operation(summary = "로그인 체크", description = "로그인 체크 API 입니다.")
    public ResponseEntity<?> checkLogin(HttpServletRequest request){
        return userService.checkLogin(request);
    }
}
