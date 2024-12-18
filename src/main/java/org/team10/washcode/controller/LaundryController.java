package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team10.washcode.ResponseDTO.laundry.ShopAddResDTO;
import org.team10.washcode.service.LaundryService;

@RestController
@RequestMapping("/api")
public class LaundryController {

    @Autowired
    private LaundryService laundryService;

    @PostMapping("/laundry")
    public ResponseEntity<?> registerLaundry(@RequestBody ShopAddResDTO to) {
        // 여기서 데이터 저장, 유효성 검사 등 로직 처리
        System.out.println("받은 데이터: " + to);

        laundryService.registerLaundryShop(to);

        // 성공 응답 반환
        return ResponseEntity.ok().body("등록 완료");
    }
}
