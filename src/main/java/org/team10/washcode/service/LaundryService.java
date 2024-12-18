package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.ResponseDTO.laundry.ShopAddResDTO;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.repository.LaundryShopRepository;

@Service
public class LaundryService {

    @Autowired
    private LaundryShopRepository laundryShopRepository;

    public LaundryShop registerLaundryShop(ShopAddResDTO to) {
        LaundryShop shop = new LaundryShop();
        shop.setShop_name(to.getShop_name());
        shop.setBusiness_number(to.getBusiness_number());
        shop.setAddress(to.getAddress());
        shop.setPhone(to.getPhone());
        shop.setOperating_days(to.getOperating_days());
        shop.setBusiness_hours(to.getBusiness_hours());
        shop.setLatitude(to.getLatitude());
        shop.setLongitude(to.getLongitude());

        return laundryShopRepository.save(shop);
    }
}
