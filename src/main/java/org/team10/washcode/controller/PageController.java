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
import org.team10.washcode.ResponseDTO.pickup.PickupDeliveryResDTO;
import org.team10.washcode.RequestDTO.user.KakaoUserDataDTO;
import org.team10.washcode.service.KakaoService;
import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupResDTO;
import org.team10.washcode.service.PickupService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class PageController {

    private final KakaoService kakaoService;
    private final PickupService pickupService;

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

    @RequestMapping("/shop-main")
    public String shopMain(Model model) {
        return "Shop/shop-main";
    }

    @RequestMapping("/pickup-check")
    public String pickupCheck(Model model) {
        List<PickupDetailResDTO> pickupList = pickupService.getPickupList(1L);
        model.addAttribute("pickupList", pickupList);
        return "Shop/pickup-check";
    }

    @RequestMapping("/pickup-list")
    public String pickupList(Model model) {
        List<PickupResDTO> pickedUpList = pickupService.getPickedupListAndUpdateStatus(1L);
        model.addAttribute("pickedUpList", pickedUpList);
        return "Shop/pickup-list";
    }

    @RequestMapping("/pickup-detail")
    public String pickupDetail(@RequestParam("id") Long pickupId, Model model) {
        PickupResDTO pickupDetail = pickupService.getPickupDetail(pickupId);
        model.addAttribute("pickupDetail", pickupDetail);
        return "Shop/pickup-detail";
    }

    @RequestMapping("/pickup-delivery")
    public String pickupDelivery(Model model) {
        List<PickupDeliveryResDTO> pickupList = pickupService.getPickupDeliveryList(1L);
        model.addAttribute("pickupList", pickupList);
        return "Shop/pickup-delivery";
    }

    @RequestMapping("/sales-summary")
    public String salesSummary(Model model) {
        return "Shop/sales-summary";
    }

    @RequestMapping("/shop-review")
    public String shopReview(Model model) {
        return "Shop/shop-review";
    }

    @RequestMapping("/modify-shop-info")
    public String modifyShopInfo(Model model) {
        return "Shop/modify-shop-info";
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
