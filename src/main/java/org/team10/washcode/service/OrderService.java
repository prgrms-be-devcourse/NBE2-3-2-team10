package org.team10.washcode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.RequestDTO.order.OrderItemReqDTO;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.ResponseDTO.order.OrderlistResDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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

    // OrderService
//    public List<OrderlistResDTO> getOrdersByUserId(int userId) {
//        return pickupRepository.getOrdersByUserId(userId);  // 바로 DTO 리스트 반환
//    }

   public List<OrderlistResDTO> getOrdersByUserId(int userId) {
        List<Object[]> result = pickupRepository.findOrderListByUserId(userId);

        return result.stream().map(row->new OrderlistResDTO(
                (int) row[1],   //pickup_id
                (String) row[0],    //shop_name
                (PickupStatus) row[2],  //status
                (Timestamp) row[3]  //created_at
        )).toList();
   }





}