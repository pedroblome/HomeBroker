package com.pedroblome.user.service;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.text.AbstractDocument.Content;

import com.pedroblome.user.controller.dto.Stockdto;
//import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.UserRepository;
import com.pedroblome.user.repository.User_orderRepository;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import ch.qos.logback.core.util.ContentTypeUtil;

//import reactor.core.publisher.Mono;

@Service
public class User_orderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User_orderRepository User_orderRepository;

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

    public Boolean checkStock(Stockdto stockdto) {
        RestTemplate restTemplate = new RestTemplate();

        // URL DESTINO
        URI uri;
        try {
            uri = new URI("http://localhost:8089/stocks/dto/" + stockdto.getId());

            // CABEÇALHO
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            // headers.set("atuhorzition, "token")

            // (instancia,cabecalho)
            HttpEntity requestEntity = new HttpEntity(stockdto, headers);

            // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
            ResponseEntity<Boolean> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
                    Boolean.class);
            return response.getBody();

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;

    }

}
