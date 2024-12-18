package org.team10.washcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    public String index() {
        return "main";
    }

    @RequestMapping("register-shop")
    public String registerShop() {
        return "Shop/register-shop";
    }

}
