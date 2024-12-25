package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.laundry.ShopAddReqDTO;
import org.team10.washcode.ResponseDTO.laundry.HandledItemsResDTO;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.HandledItems;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.service.LaundryService;

import java.util.List;
import java.util.Map;

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


    //세탁소 정보 저장 및 수정
    @PostMapping("/info")
    public ResponseEntity<?> registerLaundry(@RequestBody ShopAddReqDTO to) {

        int laundry_id = laundryService.registerLaundryShop(to);

        // 성공 응답 반환
        return ResponseEntity.ok().body(Map.of("laundry_id", laundry_id));
    }

    //가격표 저장 및 수정
    @PostMapping("/handled-items")
    public List<HandledItems> setHandledItems(@RequestBody List<HandledItemsResDTO> itemsList) {
        System.out.println("Received items list: " + itemsList);

        return laundryService.setHandledItems(itemsList);
    }




}
