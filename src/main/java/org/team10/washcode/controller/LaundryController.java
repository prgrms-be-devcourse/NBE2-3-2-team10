package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.service.LaundryService;

import java.util.List;

@RestController
@RequestMapping("/api/laundry/")
public class LaundryController {

    @Autowired
    private LaundryService laundryService;

    @GetMapping("map")
    public List<LaundryShop> map(@RequestParam(value = "shop_name", required = false) String shop_name) {
        if(shop_name != null && !shop_name.isEmpty()) {
            return laundryService.getLaundryShops(shop_name);
        } else
            return laundryService.getLaundryShops();
    }

}
