package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.RequestDTO.order.OrderItemReqDTO;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.Pickup;
import org.team10.washcode.entity.PickupItem;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.*;

import java.security.Timestamp;

@Service
public class OrderService {
    @Autowired
    private PickupRepository pickupRepository;
    @Autowired
    private PickupItemRepository pickupItemRepository;
    @Autowired
    private UserRepository userRepository;  // User 조회용
    @Autowired
    private LaundryShopRepository laundryShopRepository;
    @Autowired
    private HandledItemsRepository handledItemsRepository;

    public void saveOrder(Pickup pickup, PickupItem pickupItem) {
        // Pickup 저장
        Pickup savedPickup = pickupRepository.save(pickup);

        // PickupItem 저장 (Pickup과 연결된 상태로 저장)
        pickupItem.setPickup(savedPickup); // 생성된 Pickup을 PickupItem에 연결
        pickupItemRepository.save(pickupItem); // PickupItem 저장
    }

}
