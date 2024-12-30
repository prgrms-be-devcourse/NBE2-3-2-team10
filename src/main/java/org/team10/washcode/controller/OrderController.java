package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.ResponseDTO.order.OrderResDTO;
import org.team10.washcode.ResponseDTO.order.OrderlistResDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.service.*;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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



    @GetMapping("/main")
    public String main() {
        return "Customer/main";
    }

    @GetMapping("/create")
    public String order(
            @RequestParam("id") int userId,
//            @AuthenticationPrincipal int userId,
            @RequestParam("laundryShopId") Long laundryShopId,
            Model model) {
        // User, LaundryShop 조회
        User user = userService.getUserById(userId);
        LaundryShop laundryShop = laundryService.getLaundryById(laundryShopId);

        // LaundryShopId에 맞는 HandledItems 리스트 조회
        List<HandledItems> handledItems = handledItemsService.getAllHandledItems(laundryShopId);

        model.addAttribute("user", user);
        model.addAttribute("laundryShop", laundryShop);
        model.addAttribute("handledItems", handledItems); // 모델에 데이터 전달
        return "Customer/apply-pickup"; // JSP로 전달
    }

    @PostMapping("/create")
    //public ResponseEntity<String> createOrder(
    public String createOrder(
            @RequestParam("userId") int userId,
            @RequestParam("laundryShopId") Long laundryShopId,
            @RequestParam("quantity") int quantity,
            @RequestParam("content") String content,
            @RequestParam("item_id") Long itemId,
            @RequestParam("method") String paymentMethod // 결제 방법
    ) {

            HandledItems handledItem = handledItemsService.getHandledItemById(itemId);

            int totalPrice = handledItem.getPrice() * quantity;

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
            pickupItem.setTotalPrice(totalPrice);

            // 수거 요청 저장
            orderService.saveOrder(pickup, pickupItem);

            // 6. 결제 정보 저장
            Payment payment = new Payment();
            payment.setPickup(pickup);
            payment.setPayment_datetime(new Timestamp(System.currentTimeMillis()));
            payment.setAmount(totalPrice); // 총 금액
            payment.setMethod(paymentMethod); // 결제 방법

            orderService.savePayment(payment);

            return "redirect:/api/orders/main";

    }

    /*
    @GetMapping("/history/{userId}")
    public String getOrderByUserId(@PathVariable int userId,Model model){
        List<OrderlistResDTO> orderList = orderService.getOrdersByUserId(userId);

        model.addAttribute("orders", orderList);
        model.addAttribute("userId",userId);

        return "Customer/order-history";
    }


    @GetMapping("/history/{userId}/{pickupId}")
    public String getOrderDetails(
            @PathVariable int userId,
            @PathVariable int pickupId,
            Model model) {
        OrderResDTO orderDetails = orderService.getOrderDetail(userId, pickupId);
        model.addAttribute("order",orderDetails);
        //return ResponseEntity.ok(orderDetails);
        return "Customer/order-history-detail";
    }*/


    @PostMapping("/cancel/{userId}/{pickupId}")
    public String cancelPickup(
            @PathVariable int pickupId,
            @PathVariable int userId) {
        // 서비스 호출
        orderService.cancelOrder(pickupId, userId);
//        return ResponseEntity.ok("Pickup status updated to CANCELLED successfully.");
        return "redirect:/api/orders/main";
    }

    //결제내역 조회
//    @GetMapping("/payment/{userId}")
//    public String Payment(@PathVariable int userId,
//                          @RequestParam(value = "filter", required = false) int filter,
//                          Model model) {
////        List<OrderlistResDTO> orderList = orderService.getOrdersByUserId(userId);
//        List<OrderlistResDTO> orderList;
//
//        if (filter != null) {
//            // 현재 시간 기준으로 filter개월 전 날짜 계산
//            Timestamp fromDate = Timestamp.valueOf(LocalDateTime.now().minusMonths(filter));
//            orderList = orderService.getOrdersByUserIdAndDate(userId, fromDate);
//        } else {
//            // 필터가 없는 경우 전체 데이터 조회
//            orderList = orderService.getOrdersByUserId(userId);
//        }
//
//        model.addAttribute("orders", orderList);
//        model.addAttribute("userId",userId);
//        model.addAttribute("filter", filter);
//        return "Customer/payment-history";
//    }

/*
    @GetMapping("/payment/{userId}")
    public String getPaymentHistory(@PathVariable int userId,
                                    @RequestParam(value = "filter", required = false) Integer filter,
                                    Model model) {
        List<OrderlistResDTO> orderList;

        if (filter != null) {
            // 현재 시간 기준으로 filter개월 전 날짜 계산
            Timestamp fromDate = Timestamp.valueOf(LocalDateTime.now().minusMonths(filter));
            orderList = orderService.getOrdersByUserIdAndDate(userId, fromDate);
        } else {
            // 필터가 없는 경우 전체 데이터 조회
            orderList = orderService.getOrdersByUserId(userId);
        }

        model.addAttribute("orders", orderList);
        model.addAttribute("userId", userId);
        model.addAttribute("filter", filter); // 현재 선택된 필터 값 전달
        return "Customer/payment-history";
    }



    //결제내역 상세조회
    @GetMapping("/payment/{userId}/{pickupId}")
    public String PaymentHistory(
            @PathVariable int userId,
            @PathVariable int pickupId,
            Model model) {
        OrderResDTO orderDetails = orderService.getOrderDetail(userId, pickupId);
        model.addAttribute("order",orderDetails);
        return "Customer/payment-history-detail";
    }
*/
}
