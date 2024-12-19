package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.RequestDTO.order.OrderItemReqDTO;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.Pickup;
import org.team10.washcode.entity.PickupItem;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.LaundryShopRepository;
import org.team10.washcode.repository.PickupItemRepository;
import org.team10.washcode.repository.PickupRepository;
import org.team10.washcode.repository.UserRepository;
import org.team10.washcode.service.LaundryService;
import org.team10.washcode.service.OrderService;
import org.team10.washcode.service.PickupService;
import org.team10.washcode.service.UserService;
import org.springframework.ui.Model;

import java.sql.Timestamp;


@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @Autowired
    private LaundryService laundryService;

    //이용 신청 페이지 //create?id={}&laundryShopId={}
    @RequestMapping("/create")
    public String order(@RequestParam("id") int userId,
                        @RequestParam("laundryShopId") Long laundryShopId,
                        Model model) {
        // 로그 출력
        System.out.println("Received laundryShopId: " + laundryShopId);

        User user = userService.getUserById(userId);
        LaundryShop laundryShop = laundryService.getLaundryById(laundryShopId);

        model.addAttribute("user", user);
        model.addAttribute("laundryShop", laundryShop);

        return "apply-pickup";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @RequestParam("userId") int userId,
            @RequestParam("laundryShopId") Long laundryShopId,
            @RequestParam("quantity") int quantity,
            @RequestParam("content") String content
    ) {
        try {
            // 로그 출력
            System.out.println("userId: " + userId + ", laundryShopId: " + laundryShopId + ", quantity: " + quantity + ", content: " + content);

            // 수거 요청 생성
            Pickup pickup = new Pickup();
            pickup.setUser(userService.getUserById(userId));
            pickup.setLaundryshop(laundryService.getLaundryById(laundryShopId));
            pickup.setContent(content);
            pickup.setStatus(PickupStatus.REQUESTED);
            pickup.setCreated_at(new Timestamp(System.currentTimeMillis()));

            PickupItem pickupItem = new PickupItem();
            pickupItem.setPickup(pickup);
            pickupItem.setQuantity(quantity); // 수량 설정
            // itemId 없이 HandledItems 연결 부분을 생략

            // 수거 요청 저장
            orderService.saveOrder(pickup, pickupItem);

            return ResponseEntity.ok("수거 요청이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수거 요청 처리 중 오류가 발생했습니다.");
        }
    }




    //이용내역 조회(상세)
    @RequestMapping("/history")
    public String orderHistory() {
        return "order-history";
    }

    //이용내역 조회(상세)
    @RequestMapping("/history/detail")
    public String orderHistory_datail() {
        return "order-history-detail";
    }

}
