package org.team10.washcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.service.OrderService;

@RestController
@RequestMapping("/api/test")
public class OrderRController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal int id){
        return orderService.getOrders(id);
    }

    @GetMapping("/{pickup_id}")
    public ResponseEntity<?> getOrderDetail(@AuthenticationPrincipal int id, @PathVariable("pickup_id") int pickupId){
        return orderService.getOrdersDetail(id,pickupId);
    }

    @PutMapping
    public ResponseEntity<?> cancelOrders(@AuthenticationPrincipal int id, @PathVariable("pickup_id") int pickupId){
        return orderService.getOrders(id);
    }
}
