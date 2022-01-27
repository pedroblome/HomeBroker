package com.pedroblome.user.controller;

import java.util.List;

import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.User_orderRepository;
import com.pedroblome.user.service.User_orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    // verifica se o campos de uma order são validos.
    // @PostMapping
    // public ResponseEntity<?> saveOrder(@RequestBody User_order user_order, User_orderService checkUser) {
    //     Stockdto stockdto = new Stockdto(user_order.getId_stock(),
    //             user_order.getStock_name(),
    //             user_order.getStock_symbol());
    //     if (user_orderService.checkStock(stockdto)) {
    //         if (user_orderService.checkUser(user_order)) {
    //             if (user_order.getRemaing_volume() == null) {
    //                 user_order.setRemaing_volume(user_order.getVolume());
    //             }
    //             User_order orderSave = user_orderRepository.save(user_order);
    //             return new ResponseEntity<User_order>(orderSave, HttpStatus.CREATED);
    //         }
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    //                 "Inexistent id or insuficient balance or insuficient volume for stockSell");

    //     }
    //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    //             "stock_name or stock_symbol doesnt match with given id_stock  ");

    // }
    //@RequestHeader("Authorization") String userToken ,
    @PostMapping()
    public ResponseEntity<?> saveOrder(@RequestBody User_order user_order) {
        return user_orderService.addOrder(user_order);
    }


    // metodo para ask min
    // metodo para ask max
    // metodo para bid min
    // metodo para bid max

    // listando ordens de compra

}
