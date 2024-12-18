package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.Pickup;
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


@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @Autowired
    private LaundryService laundryService;

    //이용 신청 페이지
    @RequestMapping("/create")
    //create?id={}
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
