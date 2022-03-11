package com.pedroblome.user.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import com.nimbusds.oauth2.sdk.Response;
import com.pedroblome.user.controller.dto.BotDto;
import com.pedroblome.user.controller.dto.OrderCreateDto;
import com.pedroblome.user.model.User;
import com.pedroblome.user.model.User_stock_balance;
import com.pedroblome.user.repository.UserRepository;
import com.pedroblome.user.repository.User_orderRepository;
import com.pedroblome.user.repository.User_stock_balanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotServices {

    @Autowired
    User_orderRepository user_orderRepository;

    @Autowired
    User_stock_balanceRepository user_stock_balanceRepository;

    @Autowired
    UserRepository userRepository;

    public void createOrdersBot(String token) {
        Random random = new Random();
        while (userRepository.sevenOrMoreOrdersBot() <= 10) {

            try {
                String name = "bot" + String.valueOf(random.nextInt(100, 300));
                String email = "bot" + String.valueOf(random.nextInt(100, 300)) + "@email.com";
                String password = "passwordbot" + String.valueOf(random.nextInt(100, 300));
                BigDecimal dollar_balance = BigDecimal.valueOf(0);
                Boolean bot = true;
                BotDto botDto = new BotDto(name, email, password, dollar_balance, bot);

                RestTemplate restTemplate = new RestTemplate();
                URI uri;
                uri = new URI("http://localhost:8088/users/");
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", token);
                headers.set("Content-Type", "application/json");

                // (instancia,cabecalho)
                HttpEntity requestEntity = new HttpEntity(botDto, headers);

                // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
                ResponseEntity<BotDto> response = restTemplate.exchange(
                        uri,
                        HttpMethod.POST,
                        requestEntity,
                        BotDto.class);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }
        // criando ordem

    }

}
