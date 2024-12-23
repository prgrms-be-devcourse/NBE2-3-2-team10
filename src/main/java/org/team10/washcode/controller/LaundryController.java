package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.service.LaundryService;

import java.util.List;

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



}
