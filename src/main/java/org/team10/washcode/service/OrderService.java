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
import org.team10.washcode.repository.LaundryShopRepository;
import org.team10.washcode.repository.PickupItemRepository;
import org.team10.washcode.repository.PickupRepository;
import org.team10.washcode.repository.UserRepository;

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

    public void createOrder(OrderReqDTO orderReqDTO) {
        // 정보조회(이름,주소)
        User user = new User();
        user.setName(orderReqDTO.getName());
        user.setAddress(orderReqDTO.getAddress());

        // 2. Pickup 엔티티 생성
        Pickup pickup = new Pickup();
        //pickup.setLaundryshop(laundryShop);  // 세탁소
        pickup.setContent(orderReqDTO.getContent()); // 요청 내용
        pickup.setStatus(PickupStatus.REQUESTED); // 상태 설정 (예: 픽업신청)

        Pickup savedPickup = pickupRepository.save(pickup); // Pickup 저장

        // 3. PickupItem 엔티티 생성 (수량만 받기)
        for (OrderItemReqDTO itemReqDTO : orderReqDTO.getOrderItem()) {
            PickupItem pickupItem = new PickupItem();
            pickupItem.setPickup(savedPickup);  // 해당 Pickup에 속하는 항목
            // 세탁 항목 정보는 실제 아이템 ID로 조회하거나 추가할 수 있습니다.
            // 여기서는 세탁 항목 정보를 생략하고 수량만 설정합니다.
            pickupItem.setQuantity(itemReqDTO.getQuantity());
            pickupItem.setTotalprice(itemReqDTO.getQuantity() * 1000); // 예시로 1000원씩 설정

            pickupItemRepository.save(pickupItem); // PickupItem 저장
        }

    }
}
