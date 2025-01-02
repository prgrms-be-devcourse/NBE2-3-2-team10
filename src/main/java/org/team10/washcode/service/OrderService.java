package org.team10.washcode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team10.washcode.Enum.PickupStatus;
import org.team10.washcode.RequestDTO.order.OrderReqDTO;
import org.team10.washcode.ResponseDTO.laundry.ItemInfoResDTO;
import org.team10.washcode.ResponseDTO.order.OrderInfoResDTO;
import org.team10.washcode.ResponseDTO.order.OrderResDTO;
import org.team10.washcode.ResponseDTO.order.OrderlistResDTO;
import org.team10.washcode.entity.*;
import org.team10.washcode.repository.db.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final PickupRepository pickupRepository;
    private final UserRepository userRepository;
    private final LaundryShopRepository laundryShopRepository;
    private final HandledItemsRepository handledItemsRepository;
    private final PickupItemRepository pickupItemRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public ResponseEntity<?> cancelOrder(int userId, int pickupId) {
        int updatedRows = pickupRepository.cancleOrder(pickupId, userId);
        if (updatedRows == 0) {
            return ResponseEntity.status(500).body("No matching pickup found for pickupId: "+ pickupId + " and userId: " + userId);
        }else {
            return ResponseEntity.ok().body("취소완료");
        }
    }


    public ResponseEntity<?> getInfo(int id, int laundryId){
        OrderInfoResDTO orderInfoResDTO = new OrderInfoResDTO();
        orderInfoResDTO.setName(userRepository.findNameById(id).get());
        orderInfoResDTO.setAddress(userRepository.findAddressById(id).get());
        orderInfoResDTO.setAddress(userRepository.findAddressById(id).get());
        orderInfoResDTO.setShop_name(laundryShopRepository.findNameById(laundryId).get());

        List<ItemInfoResDTO> handledItems = handledItemsRepository.findHandledItemsByLaundryId(laundryId);

        orderInfoResDTO.setCategory(handledItems);

        return ResponseEntity.ok().body(orderInfoResDTO);
    }

    public ResponseEntity<?> createOrder(int id, OrderReqDTO orderReqDTO){
        try {
            // 주문 저장
            Pickup pickup = new Pickup();
            pickup.setUser(userRepository.findById(id).orElseThrow());
            pickup.setLaundryshop(laundryShopRepository.findById(orderReqDTO.getLaundryshop_id()).orElseThrow());
            pickup.setContent(orderReqDTO.getContent());
            pickup.setStatus(PickupStatus.REQUESTED);
            pickup.setCreated_at(new Timestamp(System.currentTimeMillis()));

            pickupRepository.save(pickup);

            // 주문 목록 저장 ( 현재는 1개만 )
            PickupItem pickupItem = new PickupItem();
            pickupItem.setPickup(pickup);
            pickupItem.setQuantity(orderReqDTO.getQuantity());

            HandledItems handledItem = handledItemsRepository.findById(orderReqDTO.getItem_id()).orElseThrow();
            pickupItem.setHandledItems(handledItem);
            pickupItem.setTotalPrice(handledItem.getPrice() * orderReqDTO.getQuantity());

            pickupItemRepository.save(pickupItem);

            // 결제 정보 저장
            Payment payment = new Payment();
            payment.setPickup(pickup);
            payment.setPayment_datetime(new Timestamp(System.currentTimeMillis()));
            payment.setAmount(handledItem.getPrice() * orderReqDTO.getQuantity()); // 총 금액
            payment.setMethod(orderReqDTO.getPaymentMethod()); // 결제 방법

            paymentRepository.save(payment);

            return ResponseEntity.ok().body(pickupRepository.findIdByMax());
        } catch (Exception e) {
            System.out.println("[Error] " + e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }

    // 추가 내역 (RestController)
    // 유저 ID 로 주문내역 조회
    public ResponseEntity<?> getOrders(int id){
        List<Object[]> result = pickupRepository.findOrderListByUserId(id);

        List<OrderlistResDTO> orderlistResDTOS = result.stream().map(row->new OrderlistResDTO(
                (int) row[1],   //pickup_id
                (String) row[0],    //shop_name
                ((PickupStatus) row[2]).getDesc(),  //status
                new SimpleDateFormat("yyyy년 MM월 dd일").format((Timestamp) row[3])  //created_at
        )).toList();

        return ResponseEntity.ok().body(orderlistResDTOS);
    }

    // 유저 ID 및 주문 ID로 주문 상세 내역 조회
    public ResponseEntity<?> getOrdersDetail(int id, int pickupId){
        List<Object[]> result = pickupRepository.findOrderDetails(id, pickupId);
        OrderResDTO orderResDTO = new OrderResDTO();

        // 주문 아이템 리스트가 null일 경우 빈 리스트로 초기화
        orderResDTO.setOrder_items(new ArrayList<>());

        for (Object[] obj : result) {
            orderResDTO.setAddress((String) obj[0]);
            orderResDTO.setPhone((String) obj[1]);
            orderResDTO.setShop_name((String) obj[2]);
            orderResDTO.setContent((String) obj[5]);
            orderResDTO.setName((String) obj[15]);

            orderResDTO.setStatus(((PickupStatus) obj[4]).getDesc());
            orderResDTO.setCreated_at(new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm").format((Timestamp) obj[6]));
            if(obj[7] != null){
                orderResDTO.setUpdate_at(new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm").format((Timestamp) obj[7]));
            }
            orderResDTO.setMethod((String) obj[14]);
            orderResDTO.setAmount((Integer)obj[13]);

            OrderResDTO.OrderItem orderItem = new OrderResDTO.OrderItem(
                    (String) obj[11], // item_name
                    (Integer) obj[9], // quantity
                    (Integer) obj[10] // totalPrice
            );
            orderResDTO.getOrder_items().add(orderItem);
        }

        return ResponseEntity.ok().body(orderResDTO);
    }

    /* 혜원님 추가 긴으
    //필터링 조회(개월수로)
    public List<OrderlistResDTO> getOrdersByUserIdAndDate(int userId, Timestamp fromDate) {
        List<Object[]> rawResults = pickupRepository.findByUserIdAndDate(userId, fromDate);

        // Object[]를 DTO로 변환
        return rawResults.stream()
                .map(result -> new OrderlistResDTO(
                        (int) result[1],   //pickup_id
                        (String) result[0],    //shop_name
                        ((PickupStatus) result[2]).getDesc(),  //status
                        (Timestamp) result[3]  //created_at
                ))
                .collect(Collectors.toList());
    }*/

}