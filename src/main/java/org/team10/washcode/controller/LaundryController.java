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

    @PostMapping("/laundry")
    public ResponseEntity<?> registerLaundry(@RequestBody ShopAddReqDTO to) {
        // 여기서 데이터 저장, 유효성 검사 등 로직 처리
        System.out.println("받은 데이터: " + to);

        laundryService.registerLaundryShop(to);

        // 성공 응답 반환
        return ResponseEntity.ok().body("등록 완료");
    }

}
