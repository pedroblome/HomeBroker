package com.pedroblome.user.controller;

import java.io.Console;
import java.util.List;

import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.User_orderRepository;
import com.pedroblome.user.service.User_orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users_order")
public class User_orderController {

    @Autowired
    private User_orderRepository user_orderRepository;

    @Autowired
    private User_orderService user_orderService;

    @GetMapping
    public List<User_order> list() {
        return user_orderRepository.findAll();
    }
  

    @PostMapping()
    public ResponseEntity<?> saveOrder(@RequestHeader("Authorization") String token,
            @RequestBody User_order user_order) {
        return user_orderService.addOrder(user_order, token);
    }

}
