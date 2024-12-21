package org.team10.washcode.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.user.LoginReqDTO;
import org.team10.washcode.RequestDTO.user.RegisterReqDTO;
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

    // 임시
    @GetMapping
    @Operation(summary = "회원정보 조회", description = "회원정보를 조회하는 API 입니다.")
    public ResponseEntity<?> getUser(@CookieValue(value = "ACCESSTOKEN") Cookie cookie){
        Long id = Long.parseLong(cookie.getValue());
        return userService.getUser(id);
    }

    @GetMapping("/role")
    @Operation(summary = "회원등급 조회", description = "회원의 등급만 조회하는 API 입니다")
    public ResponseEntity<?> getUserRole(@CookieValue(value = "ACCESSTOKEN") Cookie cookie){
        return userService.getUserRole(cookie);
    }

    @GetMapping("/address")
    @Operation(summary = "회원주소 조회", description = "회원의 주소만 조회하는 API 입니다")
    public ResponseEntity<?> getUserAddress(@CookieValue(value = "ACCESSTOKEN") Cookie cookie){
        return userService.getUserAddress(cookie);
    }
}
