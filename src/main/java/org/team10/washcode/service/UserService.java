package org.team10.washcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.team10.washcode.RequestDTO.user.RegisterReqDTO;
import org.team10.washcode.entity.User;
import org.team10.washcode.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> signup(RegisterReqDTO registerReqDTO){
        try {
            if(userRepository.findByEmailExists(registerReqDTO.getEmail())>0){
                return ResponseEntity.status(409).body("이메일이 중복되었습니다.");
            }

            User user = new User();
            user.setEmail(registerReqDTO.getEmail());
            user.setPassword(registerReqDTO.getPassword());
            user.setName(registerReqDTO.getName());
            user.setAddress(registerReqDTO.getAddress());
            user.setPhone(registerReqDTO.getPhone());
            user.setRole(registerReqDTO.getRole());

            userRepository.save(user);

            return ResponseEntity.ok().body("회원가입에 성공했습니다.");
        } catch (Exception e) {
            System.out.println("[Error] "+e.getMessage());
            return ResponseEntity.status(500).body("DB 에러");
        }
    }
}
