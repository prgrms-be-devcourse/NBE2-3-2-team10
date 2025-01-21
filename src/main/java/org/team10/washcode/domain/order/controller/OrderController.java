package org.team10.washcode.domain.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.domain.order.dto.KakaoPayReqDTO;
import org.team10.washcode.domain.order.dto.OrderReqDTO;
import org.team10.washcode.global.oauth2.client.KakaoPayClient;
import org.team10.washcode.domain.order.service.OrderService;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KakaoPayClient kakaoPayClient;

    @GetMapping("/info/{laundryId}")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal int id, @PathVariable("laundryId") int laundryId){
        return orderService.getInfo(id,laundryId);
    }

    @PostMapping()
    public ResponseEntity<?> creatOrder(@AuthenticationPrincipal int id, @RequestBody OrderReqDTO orderReqDTO){
        return orderService.createOrder(id, orderReqDTO);
    }

    @GetMapping
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal int id){
        return orderService.getOrders(id);
    }

    @GetMapping("/{pickupId}")
    public ResponseEntity<?> getOrderDetail(@AuthenticationPrincipal int id, @PathVariable("pickupId") int pickupId){
        return orderService.getOrdersDetail(id,pickupId);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@AuthenticationPrincipal int id, @RequestBody Map<String, Integer> pickupId){
        return orderService.cancelOrder(id,pickupId.get("pickupId"));
    }

    @PostMapping("/kakaopay/ready")
    public ResponseEntity<?> kakaoPayReady(@AuthenticationPrincipal int id, @RequestBody KakaoPayReqDTO kakaoPayReqDTO) {
        return kakaoPayClient.payReady(id, kakaoPayReqDTO);
    }

    @PostMapping("/kakaopay/approve")
    public ResponseEntity<?> kakaoPayApprove(@AuthenticationPrincipal int id, @RequestBody Map<String, String> request) {
        return kakaoPayClient.payCompleted(id, request.get("token"));
    }

    /* 혜원님 추가 작성한 기능
    결제내역 조회
    @GetMapping("/payment/{userId}")
    public String Payment(@PathVariable int userId,
                          @RequestParam(value = "filter", required = false) int filter,
                          Model model) {
        List<OrderlistResDTO> orderList = orderService.getOrdersByUserId(userId);
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
        model.addAttribute("userId",userId);
        model.addAttribute("filter", filter);
        return "Customer/payment-history";
    }


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
    */
}