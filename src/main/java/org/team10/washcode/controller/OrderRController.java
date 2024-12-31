package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.service.OrderService;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class OrderRController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/info/{laundry_id}")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal int id, @PathVariable("laundry_id") int laundryId){
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

    @GetMapping("/{pickup_id}")
    public ResponseEntity<?> getOrderDetail(@AuthenticationPrincipal int id, @PathVariable("pickup_id") int pickupId){
        return orderService.getOrdersDetail(id,pickupId);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@AuthenticationPrincipal int id, @RequestBody Map<String, Integer> pickupId){
        return orderService.cancelOrder(id,pickupId.get("pickup_id"));
    }
}
