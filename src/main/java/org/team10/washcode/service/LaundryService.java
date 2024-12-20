package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.repository.LaundryShopRepository;

import java.util.List;

@Service
public class LaundryService {
    @Autowired
    private LaundryShopRepository laundryShopRepository;

    public List<LaundryShop> getLaundryShops() {
        return laundryShopRepository.findAll();
    }

    public List<LaundryShop> getLaundryShops(String shop_name) {
        return laundryShopRepository.findByShop_NameContaining(shop_name);
    }

}
