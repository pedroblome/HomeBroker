package com.pedroblome.user.service;

import java.math.BigDecimal;

import com.pedroblome.user.model.User;
import com.pedroblome.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public  ResponseEntity<?> depositUser(User user) {
        
        BigDecimal newDollar_balance = userRepository.getById(user.getId()).getDollar_balance().add(BigDecimal.valueOf("valueDeposit"));
        userRepository.getById(user.getId()).setDollar_balance(newDollar_balance);




        // User_order orderSave = user_orderRepository.save(user_order);
        // return ResponseEntity.ok().body(orderSave);
        
    }


}
