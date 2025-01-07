package org.team10.washcode.domain.pickup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.team10.washcode.global.comm.enums.PickupStatus;
import org.team10.washcode.ResponseDTO.pickup.PickupDeliveryResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupSalesSummeryDTO;
import org.team10.washcode.domain.review.dto.ReviewResDTO;
import org.team10.washcode.domain.pickup.service.PickupService;
import org.team10.washcode.domain.review.service.ReviewService;

import java.util.Calendar;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pickup")
public class PickupController {

    private final PickupService pickupService;
    private final ReviewService reviewService;

    @GetMapping("/pickupId")
    public ResponseEntity<PickupResDTO> getPickupDetail(@RequestParam("id") Long pickupId) {
        PickupResDTO pickupDetail = pickupService.getPickupDetail(pickupId);

        return ResponseEntity.ok(pickupDetail);
    }

    @GetMapping("/pickupList/userId")
    public ResponseEntity<List<PickupDetailResDTO>> getPickupList(@AuthenticationPrincipal int id) {
        List<PickupDetailResDTO> pickupList = pickupService.getPickupList((long) id);

        return ResponseEntity.ok(pickupList);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestParam("pickupId") Long pickupId,
                                             @RequestParam("status") String statusStr) {
        PickupStatus newStatus = PickupStatus.valueOf(statusStr);
        pickupService.updatePickupStatus(pickupId, newStatus);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/pickedUpList/userId")
    public ResponseEntity<List<PickupResDTO>> getPickedUpListByUserId(@AuthenticationPrincipal int id) {
        List<PickupResDTO> pickedUpList = pickupService.getPickedupListAndUpdateStatus((long) id);

        return ResponseEntity.ok(pickedUpList);
    }

    @GetMapping("/pickupDelivery/userId")
    public ResponseEntity<List<PickupDeliveryResDTO>> getDeliveryPickupListByUserId(@AuthenticationPrincipal int id) {
        List<PickupDeliveryResDTO> pickupList = pickupService.getPickupDeliveryList((long) id);

        return ResponseEntity.ok(pickupList);
    }

    @GetMapping("/sales-summary/page")
    public ResponseEntity<List<PickupSalesSummeryDTO>> getSalesSummeryPage(@AuthenticationPrincipal int id) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        List<PickupSalesSummeryDTO> pickupList = pickupService.getPickupSalesSummery((long) id, currentYear, currentMonth);

        return ResponseEntity.ok(pickupList);
    }

    @GetMapping("/sales-summary")
    public ResponseEntity<List<PickupSalesSummeryDTO>> getSalesSummary(
            @AuthenticationPrincipal int id,
            @RequestParam("year") int year,
            @RequestParam("month") int month
    ) {
        System.out.println(pickupService.getPickupSalesSummery((long) id, year, month));
        List<PickupSalesSummeryDTO> pickupList = pickupService.getPickupSalesSummery((long) id, year, month);

        return ResponseEntity.ok(pickupList);
    }

    @GetMapping("/shopReview")
    public ResponseEntity<List<ReviewResDTO>> getReview(@AuthenticationPrincipal int id) {
        List<ReviewResDTO> reviewList = reviewService.getReviewList(1);

        return ResponseEntity.ok(reviewList);
    }
}
