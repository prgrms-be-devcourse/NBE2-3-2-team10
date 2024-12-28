package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.team10.washcode.Enum.LaundryCategory;
import org.team10.washcode.RequestDTO.laundry.ShopAddReqDTO;
import org.team10.washcode.ResponseDTO.laundry.HandledItemsResDTO;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.HandledItems;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.repository.HandledItemsRepository;
import org.team10.washcode.service.HandledItemsService;
import org.team10.washcode.service.LaundryService;

import java.util.*;

@RestController
@RequestMapping("/api/laundry")
public class LaundryController {

    @Autowired
    private LaundryService laundryService;
    @Autowired
    private HandledItemsService handledItemsService;
    @Autowired
    private HandledItemsRepository handledItemsRepository;

    @GetMapping("/map")
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

    //세탁소 정보가 이미 저장되어있는지 확인
    @GetMapping("/")
    public LaundryDetailResDTO checkLaundryExists(@AuthenticationPrincipal int id) {
        System.out.println("checkLaundryExists: " + id);

        return laundryService.getLaundryShopByUserId(id);
    }

    //세탁소 정보 저장
    @PostMapping("/")
    public ResponseEntity<?> registerLaundry(@RequestBody ShopAddReqDTO to, @AuthenticationPrincipal int id) {
        System.out.println(to.getUser_name());


        int laundry_id = laundryService.registerLaundryShop(to, id);

        // 성공 응답 반환
        return ResponseEntity.ok().body(Map.of("laundry_id", laundry_id));
    }

    //가격표 저장
    @PostMapping("/handled-items")
    public List<HandledItems> setHandledItems(@RequestBody List<HandledItemsResDTO> itemsList) {
        System.out.println("Received items list: " + itemsList);

        return laundryService.setHandledItems(itemsList);
    }

    //세탁소 정보 수정
    @PutMapping("/")
    public ResponseEntity<?> modifyLaundry(@RequestBody ShopAddReqDTO to, @AuthenticationPrincipal int id) {
        System.out.println(to.getUser_name());


        int laundry_id = laundryService.registerLaundryShop(to, id);

        // 성공 응답 반환
        return ResponseEntity.ok().body(Map.of("laundry_id", laundry_id));
    }

    //가격표 수정
    @PutMapping("/handled-items")
    public List<HandledItems> setHandledItems_modify(@RequestBody List<HandledItemsResDTO> itemsList) {
        System.out.println("Received items list: " + itemsList);

        return laundryService.setHandledItems(itemsList);
    }

    //세탁소 카테고리 가져오기
    @GetMapping("/{laundry_id}")
    public Map<String, Object> getHandledItems(
            @PathVariable("laundry_id") long laundry_id,
            @AuthenticationPrincipal int id) {
        List<HandledItems> handledItems = handledItemsService.getAllHandledItems(laundry_id);

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("handledItems", handledItems);

        return response;
    }

    //카테고리별로 세탁소 list 조회
    @GetMapping("/category/{category}")
    public List<Map<String, Object>> getLaundryShopsCategory(@PathVariable("category") String category) {
        LaundryCategory laundryCategory = LaundryCategory.valueOf(category.toUpperCase());

        List<LaundryShop> shops = laundryService.findLaundryShopsByCategory(laundryCategory);


        List<Map<String, Object>> result = new ArrayList<>();

        for (LaundryShop shop : shops) {
            Map<String, Object> shopData = new HashMap<>();
            shopData.put("shop", shop);

            // 해당 세탁소의 가장 저렴한 항목 조회
            List<HandledItems> handledItems = handledItemsRepository.findByLaundryshopId((long) shop.getId());
            HandledItems cheapestItem = handledItems.stream()
                    .min(Comparator.comparing(HandledItems::getPrice))
                    .orElse(null);

            shopData.put("cheapestItem", cheapestItem);

            result.add(shopData);
        }

        return result;    }



}
