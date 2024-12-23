package org.team10.washcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    public String index() {
        return "main";
    }

    @RequestMapping("laundryshop-by-map")
    public String laundryshopByMap() {
        return "Customer/laundryshop-by-map";
    }

    @RequestMapping("register-shop")
    public String registerShop() {
        return "Shop/register-shop";
    }

    @RequestMapping("main")
    public String main() {
        return "Customer/main";
    }

    @RequestMapping("laundryshop-detail/{laundry_id}")
    public String laundryshopDetail() {
        return "Customer/laundryshop-detail";
    }
}
