package com.pedroblome.user.controller;

import com.pedroblome.user.service.BotServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/bot_orders")
public class BotController {

    @Autowired
    private BotServices botServices;

    @GetMapping
    public void createOrders(@RequestHeader("Authorization") String token) {
       botServices.createOrdersBot(token); 
    }
    
}
