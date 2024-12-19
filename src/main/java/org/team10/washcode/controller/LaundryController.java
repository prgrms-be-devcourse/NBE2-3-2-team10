package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.service.LaundryService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class LaundryController {

    @Autowired
    private LaundryService laundryService;

    @GetMapping("laundry/map")
    public List<LaundryShop> map() {

        return laundryService.getLaundryShops();
    }

}
