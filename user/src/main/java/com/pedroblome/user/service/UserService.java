package com.pedroblome.user.service;

import java.math.BigDecimal;

import com.pedroblome.user.controller.dto.AppendDto;
import com.pedroblome.user.model.User;
import com.pedroblome.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public  ResponseEntity<?> depositUser(AppendDto appendDto) {
        
        BigDecimal newDollar_balance = userRepository.getById(appendDto.id).getDollar_balance().add((appendDto.amount));
        userRepository.getById(appendDto.id).setDollar_balance(newDollar_balance);
        User userSaveDeposit = userRepository.save(userRepository.getById(appendDto.id));
        
        return ResponseEntity.ok().body(userSaveDeposit);




        // User_order orderSave = user_orderRepository.save(user_order);
        // return ResponseEntity.ok().body(orderSave);
        
    }


}
