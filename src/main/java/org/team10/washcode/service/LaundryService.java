package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.Enum.LaundryCategory;
import org.team10.washcode.RequestDTO.laundry.ShopAddReqDTO;
import org.team10.washcode.ResponseDTO.laundry.HandledItemsResDTO;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.HandledItems;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.db.HandledItemsRepository;
import org.team10.washcode.repository.db.LaundryShopRepository;
import org.team10.washcode.repository.db.UserRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LaundryService {
    @Autowired
    private LaundryShopRepository laundryShopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HandledItemsRepository handledItemsRepository;

    public LaundryShop getLaundryById(Long id) {
        return laundryShopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LaundryShop not found"));
    }

    public List<LaundryShop> getLaundryShops(double userLat, double userLng) {
        List<LaundryShop> shops = laundryShopRepository.findAll();
        return sortByDistance(shops, userLat, userLng);
    }

    // 검색된 세탁소 리스트 거리순 정렬
    public List<LaundryShop> getLaundryShops(String shop_name, double userLat, double userLng) {
        List<LaundryShop> shops = laundryShopRepository.findByShop_NameContaining(shop_name);
        return sortByDistance(shops, userLat, userLng);
    }

    // 거리 계산 및 정렬
    private List<LaundryShop> sortByDistance(List<LaundryShop> shops, double userLat, double userLng) {
        return shops.stream()
                .sorted((shop1, shop2) -> {
                    double distance1 = calculateDistance(userLat, userLng, shop1.getLatitude(), shop1.getLongitude());
                    double distance2 = calculateDistance(userLat, userLng, shop2.getLatitude(), shop2.getLongitude());
                    return Double.compare(distance1, distance2);
                })
                .collect(Collectors.toList());
    }

    // Haversine 공식을 사용하여 거리 계산
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 지구 반지름 (km)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // 거리 반환 (km)

    }

    //세탁소 상세정보 조회
    //세탁소 id로 세탁소 정보 찾기
    public LaundryDetailResDTO getLaundryShopById(int id) {
        LaundryShop laundryShop = laundryShopRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("LaundryShop not found"));

        LaundryDetailResDTO to = new LaundryDetailResDTO();

        System.out.println("LaundryDetailResDTO: " + laundryShop.getId());
        to.setShop_name(laundryShop.getShop_name());
        to.setPhone(laundryShop.getPhone());
        to.setAddress(laundryShop.getAddress());
        to.setNon_operating_days(laundryShop.getNon_operating_days());
        to.setBusiness_number(laundryShop.getBusiness_number());
        to.setUser_name(laundryShop.getUser_name());


        List<HandledItems> handledItems = handledItemsRepository.findByLaundryshopId((long) laundryShop.getId());
        to.setHandledItems(handledItems);

        return to;
    }

    //user_id로 세탁소 정보 찾기
    public LaundryDetailResDTO getLaundryShopByUserId(int id) {
        LaundryShop laundryShop = laundryShopRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("LaundryShop not found"));

        LaundryDetailResDTO to = new LaundryDetailResDTO();

        System.out.println("LaundryDetailResDTO: " + laundryShop.getId());
        to.setShop_name(laundryShop.getShop_name());
        to.setPhone(laundryShop.getPhone());
        to.setAddress(laundryShop.getAddress());
        to.setNon_operating_days(laundryShop.getNon_operating_days());
        to.setBusiness_number(laundryShop.getBusiness_number());
        to.setUser_name(laundryShop.getUser_name());


        List<HandledItems> handledItems = handledItemsRepository.findByLaundryshopId((long) laundryShop.getId());
        to.setHandledItems(handledItems);

        return to;
    }

    //카테고리로 세탁소 정보 찾기
     public List<LaundryShop> findLaundryShopsByCategory(LaundryCategory category) {
        // HandledItems에서 카테고리에 맞는 세탁소 ID 리스트 가져오기
        List<Integer> shopIds = handledItemsRepository.findLaundryShopIdsByCategory(category);

        // 세탁소 정보 가져오기
        return laundryShopRepository.findByIdIn(shopIds);
    }

    //세탁소 저장하기
    public int registerLaundryShop(ShopAddReqDTO to, int id) {
        User user = userRepository.findById(id).orElse(null);
        LaundryShop shop = laundryShopRepository.findByUserId(id)
                .orElseGet(LaundryShop::new);

        shop.setUser(user);
        shop.setShop_name(to.getShop_name());
        shop.setBusiness_number(to.getBusiness_number());
        shop.setUser_name(to.getUser_name());
        shop.setAddress(to.getAddress());
        shop.setPhone(to.getPhone());
        shop.setNon_operating_days(to.getNon_operating_days());
        shop.setLatitude(to.getLatitude());
        shop.setLongitude(to.getLongitude());
        shop.setCreated_at(new Timestamp(System.currentTimeMillis()));

        LaundryShop savedShop = laundryShopRepository.save(shop);

        return savedShop.getId();
    }


    //가격표 정보 등록 및 수정
    public List<HandledItems> setHandledItems(List<HandledItemsResDTO> items) {
        Long laundryId = (long) items.get(0).getLaundry_id();
        LaundryShop laundryShop = laundryShopRepository.findById(laundryId)
                .orElseThrow(() -> new IllegalArgumentException("LaundryShop not found with ID: " + laundryId));


        //laundry_id로 이미 저장되어있는 가격표가 있다면 불러옴
        List<HandledItems> handledItemsList = handledItemsRepository.findByLaundryshopId(laundryId);


        //없으면 새로 생성
        if (handledItemsList == null) {
            handledItemsList = new ArrayList<>();
        }

        List<HandledItems> toBeDeletedItems = handledItemsList.stream()
                .filter(existingItem -> !items.stream()
                        .anyMatch(item -> item.getItem_name().equals(existingItem.getItem_name()) &&
                                item.getCategory().equals(existingItem.getCategory())))
                .collect(Collectors.toList());

        // 삭제할 항목들 삭제
        for (HandledItems itemToDelete : toBeDeletedItems) {
            handledItemsRepository.delete(itemToDelete); // 해당 항목 삭제
            handledItemsList.remove(itemToDelete); // 로컬 리스트에서 삭제된 항목 제거
        }

        for (HandledItemsResDTO item : items) {
            // 기존 항목이 있는지 찾아보기
            Optional<HandledItems> existingItemOptional = handledItemsList.stream()
                    .filter(existingItem -> existingItem.getItem_name().equals(item.getItem_name()) &&
                            existingItem.getCategory().equals(item.getCategory()))
                    .findFirst();

            HandledItems handledItem;

            if (existingItemOptional.isPresent()) {
                // 기존 항목이 있다면 업데이트
                handledItem = existingItemOptional.get();

                handledItem.setItem_name(item.getItem_name());
                handledItem.setCategory(item.getCategory());
                handledItem.setPrice(item.getPrice());
            } else {
                // 기존 항목이 없다면 새로 생성
                handledItem = new HandledItems();
                handledItem.setLaundryshop(laundryShop);
                handledItem.setItem_name(item.getItem_name());
                handledItem.setCategory(item.getCategory());
                handledItem.setPrice(item.getPrice());

                handledItemsList.add(handledItem);  // 새 항목 추가
            }

            // 저장
            handledItemsRepository.save(handledItem);
        }

        // 전체 항목 저장
        return handledItemsRepository.saveAll(handledItemsList);
    }

}


