package org.team10.washcode.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.stereotype.Service;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO;
import org.team10.washcode.ResponseDTO.pickup.PickupResDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.repository.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PickupService {
    private final PickupRepository pickupRepository;
    private final PaymentRepository paymentRepository;
    private final PickupItemRepository pickupItemRepository;

    @Transactional
    public PickupResDTO getPickupDetail(Long pickupId) {
        Pickup pickup = pickupRepository.findPickupWithFetchJoin(pickupId)
                .orElseThrow(() -> new RuntimeException("해당 ID로 pickup 을 찾을 수 없습니다: " + pickupId));

        Payment payment = paymentRepository.findByPickupId(pickupId);

        List<PickupItem> pickupItems = pickupItemRepository.findByPickupId(pickupId);
        List<PickupResDTO.OrderItemDTO> orderItems = pickupItems.stream()
                .map(item -> new PickupResDTO.OrderItemDTO(
                        item.getHandledItems().getItem_name(),
                        item.getQuantity(),
                        item.getTotalprice()
                ))
                .collect(Collectors.toList());

        return new PickupResDTO(
                pickup.getId(),
                pickup.getStatus(),
                pickup.getCreated_at(),
                pickup.getUser().getAddress(),
                orderItems
        );
    }

    @Transactional
    public List<PickupDetailResDTO> getPickupList(Long userId) {
        List<Pickup> pickups = pickupRepository.findAllByUserIdWithFetchJoinAndStatus(userId, PickupStatus.REQUESTED);

        return pickups.stream().map(pickup -> {
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
                    pickup.getLaundryshop().getShop_name(),
                    pickup.getCreated_at(),
                    pickup.getUser().getAddress(),
                    pickup.getUser().getPhone(),
                    orderItems,
                    payment.getAmount(),
                    payment.getMethod()
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public void updatePickupStatus(Long pickupId, PickupStatus newStatus) {
        Pickup pickup = pickupRepository.findById(pickupId)
                .orElseThrow(() -> new RuntimeException("해당 ID로 pickup을 찾을 수 없습니다: " + pickupId));

        pickup.setStatus(newStatus);
        pickupRepository.save(pickup);
    }

    @Transactional
    public List<PickupResDTO> getPickedupListAndUpdateStatus(Long userId) {
        List<PickupStatus> statuses = Arrays.asList(
                PickupStatus.PAYMENT_PENDING,
                PickupStatus.PAYMENT_COMPLETED,
                PickupStatus.IN_PROGRESS,
                PickupStatus.CANCELLED_WITH_DELIVERY_FEE,
                PickupStatus.CANCELLED
        );

        List<Pickup> pickups = pickupRepository.findAllByUserIdAndStatuses(userId, statuses);

        return pickups.stream().map(pickup -> {

            List<PickupItem> pickupItems = pickupItemRepository.findByPickupId((long) pickup.getId());
            List<PickupResDTO.OrderItemDTO> orderItems = pickupItems.stream()
                    .map(item -> new PickupResDTO.OrderItemDTO(
                            item.getHandledItems().getItem_name(),
                            item.getQuantity(),
                            item.getTotalprice()
                    ))
                    .collect(Collectors.toList());

            if (pickup.getStatus().equals(PickupStatus.PAYMENT_COMPLETED)) {
                pickup.setStatus(PickupStatus.IN_PROGRESS);
                pickupRepository.save(pickup);
            }

            return new PickupResDTO(
                    pickup.getId(),
                    pickup.getStatus(),
                    pickup.getCreated_at(),
                    pickup.getUser().getAddress(),
                    orderItems
            );
        }).collect(Collectors.toList());
    }

}
