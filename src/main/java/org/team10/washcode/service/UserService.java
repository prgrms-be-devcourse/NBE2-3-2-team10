package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not found"));
    }
}
