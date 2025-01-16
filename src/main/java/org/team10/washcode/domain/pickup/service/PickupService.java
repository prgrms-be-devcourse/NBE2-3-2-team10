package org.team10.washcode.domain.pickup.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.team10.washcode.global.comm.enums.PickupStatus;
import org.team10.washcode.domain.pickup.dto.PickupDeliveryResDTO;
import org.team10.washcode.domain.pickup.dto.PickupDetailResDTO;
import org.team10.washcode.domain.pickup.dto.PickupResDTO;
import org.team10.washcode.domain.pickup.dto.PickupSalesSummeryDTO;
import org.team10.washcode.domain.order.entity.db.Payment;
import org.team10.washcode.domain.pickup.entity.Pickup;
import org.team10.washcode.domain.pickup.entity.PickupItem;
import org.team10.washcode.domain.order.repository.db.PaymentRepository;
import org.team10.washcode.domain.pickup.repository.PickupItemRepository;
import org.team10.washcode.domain.pickup.repository.PickupRepository;

import java.util.Arrays;
import java.util.List;
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

        List<PickupItem> pickupItems = pickupItemRepository.findByPickupId(pickupId);
        List<PickupResDTO.OrderItemDTO> orderItems = pickupItems.stream()
                .map(item -> new PickupResDTO.OrderItemDTO(
                        item.getHandledItems().getItemName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                ))
                .collect(Collectors.toList());

        return new PickupResDTO(
                pickup.getId(),
                pickup.getStatus(),
                pickup.getCreatedAt(),
                pickup.getUser().getAddress(),
                pickup.getContent(),
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
                            item.getHandledItems().getItemName(),
                            item.getQuantity(),
                            item.getTotalPrice()
                    ))
                    .collect(Collectors.toList());

            return new PickupDetailResDTO(
                    pickup.getId(),
                    pickup.getLaundryshop().getShopName(),
                    pickup.getCreatedAt(),
                    pickup.getUser().getAddress(),
                    pickup.getUser().getPhone(),
                    pickup.getContent(),
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
                            item.getHandledItems().getItemName(),
                            item.getQuantity(),
                            item.getTotalPrice()
                    ))
                    .collect(Collectors.toList());

            if (pickup.getStatus().equals(PickupStatus.PAYMENT_COMPLETED)) {
                pickup.setStatus(PickupStatus.IN_PROGRESS);
                pickupRepository.save(pickup);
            }

            return new PickupResDTO(
                    pickup.getId(),
                    pickup.getStatus(),
                    pickup.getCreatedAt(),
                    pickup.getUser().getAddress(),
                    pickup.getContent(),
                    orderItems
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public List<PickupDeliveryResDTO> getPickupDeliveryList(Long userId) {
        List<Pickup> pickups = pickupRepository.findAllByUserIdWithFetchJoinAndStatus(userId, PickupStatus.OUT_FOR_DELIVERY);

        return pickups.stream().map(pickup -> {
            List<PickupItem> pickupItems = pickupItemRepository.findByPickupId((long) pickup.getId());

            List<PickupDeliveryResDTO.OrderItemDTO> orderItems = pickupItems.stream()
                    .map(item -> new PickupDeliveryResDTO.OrderItemDTO(
                            item.getHandledItems().getItemName(),
                            item.getQuantity(),
                            item.getTotalPrice()
                    ))
                    .collect(Collectors.toList());

            return new PickupDeliveryResDTO(
                    pickup.getId(),
                    pickup.getLaundryshop().getShopName(),
                    pickup.getCreatedAt(),
                    pickup.getUser().getAddress(),
                    pickup.getUser().getPhone(),
                    pickup.getContent(),
                    orderItems
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public List<PickupSalesSummeryDTO> getPickupSalesSummery(Long userId, int year, int month) {
        List<PickupStatus> statuses = Arrays.asList(
                PickupStatus.DELIVERED,
                PickupStatus.CANCELLED_WITH_DELIVERY_FEE,
                PickupStatus.CANCELLED
        );
        List<Pickup> pickups = pickupRepository.findSalesSummeryByUserIdAndDate(userId, statuses, year, month);

        return pickups.stream().map(pickup -> {

            List<PickupItem> pickupItems = pickupItemRepository.findByPickupId((long) pickup.getId());
            List<PickupSalesSummeryDTO.OrderItemDTO> orderItems = pickupItems.stream()
                    .map(item -> new PickupSalesSummeryDTO.OrderItemDTO(
                            item.getHandledItems().getItemName(),
                            item.getQuantity(),
                            item.getTotalPrice()
                    ))
                    .collect(Collectors.toList());
            System.out.println(pickup.getId() + " " + pickup.getStatus() + " " + pickup.getCreatedAt() + " " + pickup.getUser().getAddress() + " " + orderItems.size());

            return new PickupSalesSummeryDTO(
                    pickup.getId(),
                    pickup.getStatus(),
                    pickup.getCreatedAt(),
                    pickup.getUser().getAddress(),
                    orderItems
            );
        }).collect(Collectors.toList());
    }

}
