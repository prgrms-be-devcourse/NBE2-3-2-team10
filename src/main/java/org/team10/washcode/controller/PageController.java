package org.team10.washcode.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.team10.washcode.RequestDTO.user.KakaoUserDataDTO;
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
    public String kakaoLogin(@RequestParam("code") String code, Model model) {

        KakaoUserDataDTO kakaoUserDataDTO = kakaoService.kakaoLogin(code);
        model.addAttribute("kakaoUserData", kakaoUserDataDTO);

        return "Glober/register";
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
}
