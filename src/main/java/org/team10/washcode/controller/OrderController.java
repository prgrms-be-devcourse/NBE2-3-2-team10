package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.RequestDTO.order.OrderItemReqDTO;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.repository.LaundryShopRepository;
import org.team10.washcode.repository.PickupItemRepository;
import org.team10.washcode.repository.PickupRepository;
import org.team10.washcode.repository.UserRepository;
import org.team10.washcode.service.*;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.List;


@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @Autowired
    private LaundryService laundryService;
    @Autowired
    private HandledItemsService handledItemsService;

    @GetMapping("/create")
    public String order(@RequestParam("id") int userId,
                        @RequestParam("laundryShopId") Long laundryShopId,
                        Model model) {
        // User, LaundryShop 조회
        User user = userService.getUserById(userId);
        LaundryShop laundryShop = laundryService.getLaundryById(laundryShopId);

        // LaundryShopId에 맞는 HandledItems 리스트 조회
        List<HandledItems> handledItems = handledItemsService.getItemsByLaundryShopId(laundryShopId);

        model.addAttribute("user", user);
        model.addAttribute("laundryShop", laundryShop);
        model.addAttribute("handledItems", handledItems); // 모델에 데이터 전달
        return "Customer/apply-pickup"; // JSP로 전달
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @RequestParam("userId") int userId,
            @RequestParam("laundryShopId") Long laundryShopId,
            @RequestParam("quantity") int quantity,
            @RequestParam("content") String content,
            @RequestParam("item_id") Long itemId
    ) {
        try {
            HandledItems handledItem = handledItemsService.getHandledItemById(itemId);

            // 수거 요청 생성
            Pickup pickup = new Pickup();
            pickup.setUser(userService.getUserById(userId));
            pickup.setLaundryshop(laundryService.getLaundryById(laundryShopId));
            pickup.setContent(content);
            pickup.setStatus(PickupStatus.REQUESTED);
            pickup.setCreated_at(new Timestamp(System.currentTimeMillis()));

            PickupItem pickupItem = new PickupItem();
            pickupItem.setPickup(pickup);
            pickupItem.setQuantity(quantity);
            pickupItem.setHandledItems(handledItem);

            // 수거 요청 저장
            orderService.saveOrder(pickup, pickupItem);

            return ResponseEntity.ok("수거 요청이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수거 요청 처리 중 오류가 발생했습니다.");
        }
    }

    @RequestMapping("/history")
    public String orderHistory(@RequestParam("userId") int userId, Model model) {

        return "Customer/order-history";
    }

    @RequestMapping("/history/detail")
    public String orderHistory_datail() {
        return "Customer/order-history-detail";
    }
}
