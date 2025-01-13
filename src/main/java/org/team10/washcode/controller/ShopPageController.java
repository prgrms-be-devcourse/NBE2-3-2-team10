package org.team10.washcode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopPageController {

    @RequestMapping("/shop-main")
    public String shopMain(Model model) { return "shop/shop-main"; }

    @RequestMapping("/pickup-check")
    public String pickupCheck(Model model) { return "shop/pickup-check"; }

    @RequestMapping("/pickup-list")
    public String pickupList(Model model) { return "shop/pickup-list"; }

    @RequestMapping("/pickup-detail")
    public String pickupDetail() { return "shop/pickup-detail"; }

    @RequestMapping("/pickup-delivery")
    public String pickupDelivery(Model model) { return "shop/pickup-delivery"; }

    @RequestMapping("/sales-summary")
    public String salesSummary(Model model) { return "shop/sales-summary"; }

    @RequestMapping("/shop-review")
    public String shopReview(Model model) { return "shop/shop-review"; }

    @RequestMapping("/modify-shop-info")
    public String modifyShopInfo(Model model) { return "shop/modify-shop-info"; }

    @RequestMapping("/mypage")
    public String shopMyPage() { return "shop/shop-my-page"; }

    @RequestMapping("/myInfoModify")
    public String shopInfoModify() { return "shop/my-info-modify-page"; }

    @RequestMapping("/myInfo")
    public String shopInfo() { return "shop/my-info"; }
}
