package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LaundryController {
    @RequestMapping("/laundry")
    public String laundry(HttpServletRequest request) {
        System.out.println(request.getParameter("address"));

        return "register-shop";
    }
}
