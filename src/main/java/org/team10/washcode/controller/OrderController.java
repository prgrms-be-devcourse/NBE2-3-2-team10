package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.entity.Pickup;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.LaundryShopRepository;
import org.team10.washcode.repository.PickupItemRepository;
import org.team10.washcode.repository.PickupRepository;
import org.team10.washcode.repository.UserRepository;
import org.team10.washcode.service.OrderService;
import org.team10.washcode.service.PickupService;
import org.team10.washcode.service.UserService;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    //이용 신청 페이지
    @RequestMapping("/create")
    public String order(@RequestParam("id") int userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "apply-pickup";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderReqDTO orderReqDTO) {
        orderService.createOrder(orderReqDTO);
        return ResponseEntity.ok("수거 신청이 완료되었습니다!");
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
