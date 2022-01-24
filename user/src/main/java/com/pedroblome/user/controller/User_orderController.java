package com.pedroblome.user.controller;

import java.util.List;

import com.pedroblome.user.Aux.ask;
import com.pedroblome.user.Aux.bid;
import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.User_orderRepository;
import com.pedroblome.user.service.User_orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.Order;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

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

    @PostMapping
    public ResponseEntity<User_order> checkUserAdd(@RequestBody User_order user_order, User_orderService checkUser) {
        Stockdto stockdto = new Stockdto(user_order.getId_stock(),
                user_order.getStock_name(),
                user_order.getStock_symbol());

        if (user_orderService.checkUser(user_order) && user_orderService.checkStock(stockdto)) {
            User_order orderSave = user_orderRepository.save(user_order);
            return new ResponseEntity<User_order>(orderSave, HttpStatus.CREATED);
        } else {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario ou dados da stock invalidos!");

        }

    }

    // @GetMapping("/stock/{id}")
    // public ResponseEntity<Stockdto> getStock(@PathVariable Long id)throws
    // Exception{
    // }

}
