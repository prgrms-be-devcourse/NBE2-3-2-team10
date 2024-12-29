package org.team10.washcode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.ResponseDTO.order.OrderResDTO;
import org.team10.washcode.ResponseDTO.order.OrderlistResDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.repository.db.PaymentRepository;
import org.team10.washcode.repository.db.PickupItemRepository;
import org.team10.washcode.repository.db.PickupRepository;
import org.team10.washcode.repository.db.ReviewRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private PickupRepository pickupRepository;
    @Autowired
    private PickupItemRepository pickupItemRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public void saveOrder(Pickup pickup, PickupItem pickupItem) {
        // Pickup 저장
        Pickup savedPickup = pickupRepository.save(pickup);

        // PickupItem 저장 (Pickup과 연결된 상태로 저장)
        pickupItem.setPickup(savedPickup); // 생성된 Pickup을 PickupItem에 연결
        pickupItemRepository.save(pickupItem); // PickupItem 저장
    }

    // 결제 저장 메서드
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment); // JPA로 저장
    }

    // 조회하기
   public List<OrderlistResDTO> getOrdersByUserId(int userId) {
        List<Object[]> result = pickupRepository.findOrderListByUserId(userId);

        return result.stream().map(row->new OrderlistResDTO(
                (int) row[1],   //pickup_id
                (String) row[0],    //shop_name
                (PickupStatus) row[2],  //status
                (Timestamp) row[3]  //created_at
        )).toList();
   }

    // 조회하기(상세)
    public OrderResDTO getOrderDetail(int userId, int pickupId) {
        List<Object[]> result = pickupRepository.findOrderDetails(userId, pickupId);
        OrderResDTO orderResDTO = new OrderResDTO();

        // 주문 아이템 리스트가 null일 경우 빈 리스트로 초기화
        orderResDTO.setOrder_items(new ArrayList<>());

        for (Object[] obj : result) {
            orderResDTO.setAddress((String) obj[0]);
            orderResDTO.setPhone((String) obj[1]);
            orderResDTO.setShop_name((String) obj[2]);
            orderResDTO.setContent((String) obj[5]);
            //orderResDTO.setStatus(PickupStatus.valueOf((String) obj[4]));  // 상태는 Enum으로 변환
            orderResDTO.setStatus((PickupStatus) obj[4]);
            orderResDTO.setCreated_at((Timestamp) obj[6]);
            orderResDTO.setUpdate_at((Timestamp) obj[7]);
            orderResDTO.setMethod((String) obj[14]);
            orderResDTO.setAmount((Integer)obj[13]);
//pi_id[3], pi_it=id[8],quantity[9],category[12]
            // OrderItem 추가
            OrderResDTO.OrderItem orderItem = new OrderResDTO.OrderItem(
                    (String) obj[11], // item_name
                    (Integer) obj[9], // quantity
                    (Integer) obj[10] // totalPrice
            );
            orderResDTO.getOrder_items().add(orderItem);
        }

        return orderResDTO;
    }
    @Transactional
    public void cancelOrder(int pickupId, int userId) {
        int updatedRows = pickupRepository.cancleOrder(pickupId, userId);
        if (updatedRows == 0) {
            throw new IllegalArgumentException("No matching pickup found for pickupId: " + pickupId + " and userId: " + userId);
        }
    }



}