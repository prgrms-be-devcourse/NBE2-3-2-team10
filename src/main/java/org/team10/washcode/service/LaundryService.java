package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.ResponseDTO.laundry.ShopAddResDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.LaundryShopRepository;
import org.team10.washcode.repository.UserRepository;

import java.sql.Timestamp;

@Service
public class LaundryService {

    @Autowired
    private LaundryShopRepository laundryShopRepository;
    @Autowired
    private UserRepository userRepository;

    public LaundryShop registerLaundryShop(ShopAddResDTO to) {
        User user = userRepository.findByName(to.getUser_name());
        LaundryShop shop = new LaundryShop();
        shop.setUser(user);
        shop.setShop_name(to.getShop_name());
        shop.setBusiness_number(to.getBusiness_number());
        shop.setAddress(to.getAddress());
        shop.setPhone(to.getPhone());
        shop.setOperating_days(to.getOperating_days());
        shop.setBusiness_hours(to.getBusiness_hours());
        shop.setLatitude(to.getLatitude());
        shop.setLongitude(to.getLongitude());
        shop.setCreated_at(new Timestamp(System.currentTimeMillis()));


        return laundryShopRepository.save(shop);
    }
}
