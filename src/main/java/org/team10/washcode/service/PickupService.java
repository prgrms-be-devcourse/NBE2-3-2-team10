package org.team10.washcode.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.repository.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PickupService {

    private final PickupRepository pickupRepository;
    private final UserRepository userRepository;
    private final LaundryShopRepository laundryShopRepository;
    private final PaymentRepository paymentRepository;
    private final PickupItemRepository pickupItemRepository;

    @Transactional
    public PickupDetailResDTO getPickupDetail(Long pickupId) {
        Pickup pickup = pickupRepository.findById(pickupId)
                .orElseThrow(() -> new RuntimeException("해당 ID로 pickup 을 찾을 수 없습니다: " + pickupId));

        User user = userRepository.findById((long) pickup.getUser().getId())
                .orElseThrow(() -> new RuntimeException("해당 ID로 유저를 찾을 수 없습니다: " + pickup.getUser().getId()));

        LaundryShop shop = laundryShopRepository.findById((long) pickup.getLaundryshop().getId())
                .orElseThrow(() -> new RuntimeException("해당 ID로 세탁소를 찾을 수 없습니다: " + pickup.getLaundryshop().getId()));

        Payment payment = paymentRepository.findByPickupId(pickupId);

        List<PickupItem> pickupItems = pickupItemRepository.findByPickupId(pickupId);
        List<PickupDetailResDTO.OrderItemDTO> orderItems = pickupItems.stream()
                .map(item -> new PickupDetailResDTO.OrderItemDTO(
                        item.getHandledItems().getItem_name(),
                        item.getQuantity(),
                        item.getTotalprice()
                ))
                .collect(Collectors.toList());

        return new PickupDetailResDTO(
                pickup.getId(),
                shop.getShop_name(),
                pickup.getCreated_at(),
                user.getAddress(),
                user.getPhone(),
                orderItems,
                payment.getAmount(),
                payment.getMethod()
        );
    }

    @Transactional
    public List<PickupDetailResDTO> getPickupList(Long userId) { // status 중 REQUESTED(픽업 요청) 부분만 list 로 보내주고 확인 버튼을 통해 status 변환 후 업데이트 시키기.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 ID로 사용자를 찾을 수 없습니다: " + userId));

        List<Pickup> pickups = pickupRepository.findAllById(Collections.singleton(userId));

        List<PickupDetailResDTO> pickupDetailList = pickups.stream().map(pickup -> {

            LaundryShop shop = laundryShopRepository.findById((long) pickup.getLaundryshop().getId())
                    .orElseThrow(() -> new RuntimeException("해당 ID로 세탁소를 찾을 수 없습니다: " + pickup.getLaundryshop().getId()));

            Payment payment = paymentRepository.findByPickupId((long) pickup.getId());

            List<PickupItem> pickupItems = pickupItemRepository.findByPickupId((long) pickup.getId());

            List<PickupDetailResDTO.OrderItemDTO> orderItems = pickupItems.stream()
                    .map(item -> new PickupDetailResDTO.OrderItemDTO(
                            item.getHandledItems().getItem_name(),
                            item.getQuantity(),
                            item.getTotalprice()
                    ))
                    .collect(Collectors.toList());

            return new PickupDetailResDTO(
                    pickup.getId(),
                    shop.getShop_name(),
                    pickup.getCreated_at(),
                    user.getAddress(),
                    user.getPhone(),
                    orderItems,
                    payment.getAmount(),
                    payment.getMethod()
            );
        }).collect(Collectors.toList());

        return pickupDetailList;
    }

}
