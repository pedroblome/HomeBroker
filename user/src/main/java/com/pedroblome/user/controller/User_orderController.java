package com.pedroblome.user.controller;

import java.util.List;

import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.User_orderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users_order")
public class User_orderController {

    @Autowired
    private User_orderRepository user_orderRepository;

    @GetMapping
    public List<User_order> list(){
        return user_orderRepository.findAll();
    }

    @PostMapping
    public User_order add(@RequestBody User_order user_order){
        return user_orderRepository.save(user_order);
    }


    
}
