package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team10.washcode.service.LaundryService;

@RestController
@RequestMapping("/api/")
public class LaundryController {

    @Autowired
    private LaundryService laundryService;

    @RequestMapping("laundry/map")
    public String map() {
        return "laundryshop-by-map";
    }

    @RequestMapping("/laundry")
    public String laundry(HttpServletRequest request) {
        System.out.println(request.getParameter("address"));

        return "register-shop";
    }
}
