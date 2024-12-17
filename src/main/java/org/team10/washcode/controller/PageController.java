package org.team10.washcode.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PageController {
    public String index() {
        return "main";
    }
}
