package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.entity.LaundryShop;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.LaundryShopRepository;


@Service
public class LaundryService {
    @Autowired
    private LaundryShopRepository laundryShopRepository;
    public LaundryShop getLaundryById(Long id){
        return laundryShopRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("LaundryShop not found"));
    }
}
