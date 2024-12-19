package org.team10.washcode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.service.PickupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pickup")
public class PickupController {

    private final PickupService pickupService;

    @GetMapping("pickupId/{id}")
    public ResponseEntity<PickupDetailResDTO> getPickupDetail(@PathVariable Long id) {
        PickupDetailResDTO pickupDetail = pickupService.getPickupDetail(id);
        return ResponseEntity.ok(pickupDetail);
    }

    @GetMapping("userId/{id}")
    public ResponseEntity<List<PickupDetailResDTO>> getPickupList(@PathVariable Long id) {
        List<PickupDetailResDTO> pickupList = pickupService.getPickupList(id);
        return ResponseEntity.ok(pickupList);
    }
}
