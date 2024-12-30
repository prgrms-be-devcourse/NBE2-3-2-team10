package org.team10.washcode.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.team10.washcode.Enum.LaundryCategory;
import org.team10.washcode.RequestDTO.user.KakaoUserDataDTO;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.ResponseDTO.order.OrderResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupDeliveryResDTO;
import org.team10.washcode.entity.HandledItems;
import org.team10.washcode.ResponseDTO.review.ReviewResDTO;
import org.team10.washcode.service.*;
import org.team10.washcode.ResponseDTO.pickup.PickupSalesSummeryDTO;
import org.team10.washcode.service.KakaoService;

import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupResDTO;


import java.util.Calendar;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class PageController {

    private final KakaoService kakaoService;
    private final PickupService pickupService;
    private final LaundryService laundryService;
    private final HandledItemsService handledItemsService;
    private final ReviewService reviewService;


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

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        List<PickupSalesSummeryDTO> pickupList = pickupService.getPickupSalesSummery(1L, currentYear, currentMonth);
        model.addAttribute("pickupList", pickupList);
        return "Shop/sales-summary";
    }

    @RequestMapping("/shop-review")
    public String shopReview(Model model) {
        List<ReviewResDTO> reviewList = reviewService.getReviewList(1);
        model.addAttribute("reviewList", reviewList);
        return "Shop/shop-review";
    }

    @RequestMapping("/modify-shop-info")
    public String modifyShopInfo(Model model) {
        return "Shop/modify-shop-info";
    }


    @RequestMapping("/main")
    public String main() { return "Customer/main"; }

    @RequestMapping("/mypage")
    public String mypage() { return "Customer/my-page"; }

    @RequestMapping("/orderHistory")
    public String orderHistory() { return "Customer/order-history"; }

    @RequestMapping("/orderHistory/{pickup_id}")
    public String orderHistoryDetail(@PathVariable("pickup_id")int pickup_id, Model model){
        model.addAttribute("pickupId",pickup_id);
        return "Customer/order-history-detail";
    }

    @RequestMapping("/myInfo")
    public String myInfo() { return "Customer/my-info"; }

    @RequestMapping("/myInfoModify")
    public String myInfoModify() { return "Customer/my-info-modify-page"; }

    @RequestMapping("laundryshop-by-map")
    public String laundryshopByMap() {
        return "Customer/laundryshop-by-map";
    }

    @RequestMapping("/laundryshop-detail/{laundry_id}")
    public String laundryshopDetail(@PathVariable("laundry_id")int id, Model model) {
        LaundryDetailResDTO to = laundryService.getLaundryShopById(id);
        if(to == null) {
            return "error";
        }

        model.addAttribute("laundry", to);
        model.addAttribute("laundryId", id);
        return "Customer/laundryshop-detail";
    }

    @RequestMapping("/laundryshop-by-category/{category}")
    public String laundryshopByCategory(@PathVariable("category")String category, Model model) {
        LaundryCategory laundryCategory = LaundryCategory.valueOf(category.toUpperCase());
        String categoryName = laundryCategory.getDescription();

        model.addAttribute("category", category);
        model.addAttribute("categoryName", categoryName);
        return "Customer/laundryshop-by-category";
    }

    @RequestMapping("/shop-mypage")
    public String shopMyPage() {
        return "Shop/shop-my-page";
    }
}
