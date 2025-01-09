package org.team10.washcode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.team10.washcode.domain.laundryshop.dto.LaundryDetailResDTO;
import org.team10.washcode.domain.laundryshop.service.LaundryShopService;
import org.team10.washcode.global.comm.enums.LaundryCategory;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerPageController {
    private final LaundryShopService laundryShopService;

    @RequestMapping("/main")
    public String main() { return "customer/main"; }

    @RequestMapping("/mypage")
    public String mypage() { return "customer/my-page"; }

    @RequestMapping("/myInfo")
    public String myInfo() { return "customer/my-info"; }

    @RequestMapping("/myInfoModify")
    public String myInfoModify() { return "customer/my-info-modify-page"; }

    @RequestMapping("/order/{laundryId}")
    public String order(@PathVariable("laundryId") int laundryId, Model model){
        model.addAttribute("laundryId",laundryId);
        return "customer/apply-pickup";
    }

    @RequestMapping("/order/completed")
    public String orderCompleted(@RequestParam("pg_token") String token, Model model) {
        model.addAttribute("pg_token", token);
        return "customer/order-wait";
    }

    @RequestMapping("/order/success")
    public String orderSuccess() {
        return "customer/order-complete";
    }

    @RequestMapping("/orderHistory")
    public String orderHistory() { return "customer/order-history"; }

    @RequestMapping("/orderHistory/{pickupId}")
    public String orderHistoryDetail(@PathVariable("pickupId") int pickupId, Model model){
        model.addAttribute("pickupId",pickupId);
        return "customer/order-history-detail";
    }
    
    @RequestMapping("/laundryshop-by-map")
    public String laundryshopByMap() {
        return "customer/laundryshop-by-map";
    }

    @RequestMapping("/laundryshop-by-category/{category}")
    public String laundryshopByCategory(@PathVariable("category")String category, Model model) {
        LaundryCategory laundryCategory = LaundryCategory.valueOf(category.toUpperCase());
        String categoryName = laundryCategory.getDescription();

        model.addAttribute("category", category);
        model.addAttribute("categoryName", categoryName);
        return "customer/laundryshop-by-category";
    }

    @RequestMapping("/laundryshop-detail/{laundryId}")
    public String laundryshopDetail(@PathVariable("laundryId")int laundryId, Model model) {
        LaundryDetailResDTO to = laundryShopService.getLaundryShopById(laundryId);

        if(to == null) { return "error"; }

        model.addAttribute("laundry", to);
        model.addAttribute("laundryId", laundryId);
        return "customer/laundryshop-detail";
    }
}
