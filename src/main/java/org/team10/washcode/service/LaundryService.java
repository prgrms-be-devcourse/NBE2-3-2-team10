package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.RequestDTO.laundry.ShopAddReqDTO;
import org.team10.washcode.ResponseDTO.laundry.LaundryDetailResDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.LaundryShopRepository;
import org.team10.washcode.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaundryService {
    @Autowired
    private LaundryShopRepository laundryShopRepository;
    @Autowired
    private UserRepository userRepository;

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
        LaundryShop laundryShop = laundryShopRepository.findById(id);
        LaundryDetailResDTO to = new LaundryDetailResDTO();

        to.setShop_name(laundryShop.getShop_name());
        to.setPhone(laundryShop.getPhone());
        to.setAddress(laundryShop.getAddress());
        to.setNon_operating_days(laundryShop.getNon_operating_days());

        return to;
    }

    public LaundryShop registerLaundryShop(ShopAddReqDTO to) {
        User user = userRepository.findByName(to.getUser_name());
        LaundryShop shop = new LaundryShop();
        shop.setUser(user);
        shop.setShop_name(to.getShop_name());
        shop.setBusiness_number(to.getBusiness_number());
        shop.setAddress(to.getAddress());
        shop.setPhone(to.getPhone());
        shop.setNon_operating_days(to.getNon_operating_days());
        shop.setLatitude(to.getLatitude());
        shop.setLongitude(to.getLongitude());
        shop.setCreated_at(new Timestamp(System.currentTimeMillis()));


        return laundryShopRepository.save(shop);
    }

}

