package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.team10.washcode.global.oauth2.client.KakaoClient;

@Controller
@RequiredArgsConstructor
public class CommPageController {
    private final KakaoClient kakaoClient;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.key.client-id}")
    private String kakaoApiKey;

    @RequestMapping("/")
    public String login(Model model) {
        model.addAttribute("kakaoApiKey", kakaoApiKey);
        model.addAttribute("redirectUri", redirectUri);

        return "glober/login";
    }

    @RequestMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code, Model model, HttpServletResponse response) {
        return kakaoClient.kakaoLogin(code, model, response);
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("kakaoUserData", null);
        return "glober/register";
    }
}
