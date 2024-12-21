package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.team10.washcode.service.KakaoService;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final KakaoService kakaoService;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.key.client-id}")
    private String kakaoApiKey;

    @RequestMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code, Model model, HttpServletResponse response) {
        return kakaoService.kakaoLogin(code, model, response);
    }

    @RequestMapping("/")
    public String login(Model model) {
        model.addAttribute("kakaoApiKey", kakaoApiKey);
        model.addAttribute("redirectUri", redirectUri);

        return "Glober/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("kakaoUserData", null);
        return "Glober/register";
    }

    @RequestMapping("/main")
    public String main() { return "Customer/main"; }

    @RequestMapping("/mypage")
    public String mypage() { return "Customer/my-page"; }

    @RequestMapping("/orderHistory")
    public String orderHistory() { return "Customer/order-history"; }

    @RequestMapping("/myInfo")
    public String myInfo() { return "Customer/my-info"; }
}
