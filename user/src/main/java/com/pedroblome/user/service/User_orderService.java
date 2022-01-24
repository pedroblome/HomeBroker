package com.pedroblome.user.service;

//import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

//import reactor.core.publisher.Mono;

@Service
public class User_orderService {

    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private WebClient webClient;

    // verifica usuario
    public boolean checkUser(@RequestBody User_order user_order) {
        if (userRepository.existsById(user_order.getId_user())) {
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println(" usuario existe");
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println("=========================================");
            return true;

        } else {
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println(" usuario NAO existe");
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println("=========================================");
            return false;
        }
    }

    // public Stockdto checkStock(Long id_stock) {
    //     Mono<Stockdto> monoStock = this.webClient
    //             .method(HttpMethod.GET)
    //             .uri("{id}", id_stock)
    //             .retrieve()
    //             .bodyToMono(Stockdto.class);
    //     Stockdto stock = monoStock.block();

    //     System.out.println("finalizou");

    //     return stock;
    // }

}
