package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.laundry.ShopAddReqDTO;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.service.LaundryService;

import java.util.List;

@RestController
@RequestMapping("/api/laundry/")
public class LaundryController {

    @Autowired
    private LaundryService laundryService;

    @GetMapping("map")
    public List<LaundryShop> map(
            @RequestParam(value = "shop_name", required = false) String shop_name,
            @RequestParam(value = "userLat") double userLat,
            @RequestParam(value = "userLng") double userLng
            ) {
        if(shop_name != null && !shop_name.isEmpty()) {
            return laundryService.getLaundryShops(shop_name, userLat, userLng);
        } else {
            return laundryService.getLaundryShops(userLat, userLng);
        }
    }

    //세탁소 상세정보 조회
    @GetMapping("{laundry_id}")
    public LaundryDetailResDTO get(@PathVariable("laundry_id") int laundry_id) {
        return laundryService.getLaundryShopById(laundry_id);
    }

    @PostMapping("/info")
    public ResponseEntity<?> registerLaundry(@RequestBody ShopAddReqDTO to) {
        // 여기서 데이터 저장, 유효성 검사 등 로직 처리
        System.out.println("받은 데이터: " + to);

        laundryService.registerLaundryShop(to);

        // 성공 응답 반환
        return ResponseEntity.ok().body("등록 완료");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getLaundryInfo(HttpServletRequest request) {
        try {
            // 로그인된 사용자의 ID 가져오기
            int userId = (int) request.getAttribute("userId");

            // 사용자의 세탁소 정보 가져오기
            LaundryShop userLaundryInfo = laundryService.getLaundryInfoByUserId(userId);

            // 세탁소 정보가 없을 경우
            if (userLaundryInfo == null) {
                return ResponseEntity.status(404).body("No laundry info found");
            }

            // 세탁소 정보 반환
            return ResponseEntity.ok(userLaundryInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버 오류 발생");
        }
    }

    //세탁소 가격표 정보 저장 및 수정


}
