package org.team10.washcode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupSalesSummeryDTO;
import org.team10.washcode.service.PickupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pickup")
public class PickupController {

    private final PickupService pickupService;

    @GetMapping("/pickupId/{id}")
    public ResponseEntity<PickupResDTO> getPickupDetail(@PathVariable Long id) {
        PickupResDTO pickupDetail = pickupService.getPickupDetail(id);
        return ResponseEntity.ok(pickupDetail);
    }

    @GetMapping("/pickupList/userId/{id}")
    public ResponseEntity<List<PickupDetailResDTO>> getPickupList(@PathVariable Long id) {
        List<PickupDetailResDTO> pickupList = pickupService.getPickupList(id);
        return ResponseEntity.ok(pickupList);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestParam("pickupId") Long pickupId,
                                             @RequestParam("status") String statusStr) {
        PickupStatus newStatus = PickupStatus.valueOf(statusStr);
        pickupService.updatePickupStatus(pickupId, newStatus);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/pickedUpList/userId/{id}")
    public ResponseEntity<List<PickupResDTO>> getPickedUpListByUserId(@PathVariable Long id) {
        List<PickupResDTO> pickedUpList = pickupService.getPickedupListAndUpdateStatus(id);
        return ResponseEntity.ok(pickedUpList);
    }

    @GetMapping("/sales-summary")
    public List<PickupSalesSummeryDTO> getSalesSummary(
            @RequestParam("year") int year,
            @RequestParam("month") int month
    ) {
        Long userId = 1L;
        System.out.println(pickupService.getPickupSalesSummery(userId, year, month));
        return pickupService.getPickupSalesSummery(userId, year, month);
    }
}
