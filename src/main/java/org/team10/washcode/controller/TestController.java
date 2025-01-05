package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.order.KakaoPayReqDTO;
import org.team10.washcode.exception.ResponseResult;
import org.team10.washcode.exception.ResponseState;
import org.team10.washcode.service.KakaoPayService;


@RestController
@RequiredArgsConstructor
public class TestController {
/*
    // 서버 연결 상태 확인 코드
    @GetMapping("/health")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/test")
    public ResponseResult<?> test2() {
        return new ResponseResult<>(ResponseState.NOT_FOUND);
    }

    // ResponseEntity<?> -> 모든 타입을 받을 수 있음
    // ResponseEntity.ok -> 200
    // ResponseEntity.badRequest -> 400
    // ResponseEntity.notFound -> 404
    // ResponseEntity.status(보내고 싶은 에러 코드).body(에러) -> 에러코드
 */
}

