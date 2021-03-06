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
import com.pedroblome.user.controller.dto.StockBalanceDto;
import com.pedroblome.user.controller.dto.StockCompleteDto;
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
        System.out.println("antes de veriricar a quantidade de bots");
        while (userRepository.sevenOrMoreOrdersBot() <= 10) {
            System.out.println("depois de veriricar a quantidade de bots");

            try {
                String name = "bot" + String.valueOf(random.nextInt(100, 300));
                String email = "bot" + String.valueOf(random.nextInt(100, 300)) + "@email.com";
                String password = "passwordbot" + String.valueOf(random.nextInt(100, 300));
                BigDecimal dollar_balance = BigDecimal.valueOf(0);
                Boolean bot = true;
                BotDto botDto = new BotDto(name, email, password, dollar_balance, bot);
                System.out.println(botDto.toString());
                System.out.println(token);

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
                System.out.println("depois de criar os usuarios");

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }
        for (long id_stock = 1; id_stock <= 726; id_stock++) {

            try {

                RestTemplate restTemplateStock = new RestTemplate();
                URI uriStock;
                uriStock = new URI("http://localhost:8089/stocks/" + id_stock);
                HttpHeaders headersStock = new HttpHeaders();
                headersStock.set("Authorization", token);
                headersStock.set("Content-Type", "application/json");

                // (instancia,cabecalho)
                HttpEntity requestEntityStock = new HttpEntity(headersStock);

                // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
                ResponseEntity<StockCompleteDto> responseStock = restTemplateStock.exchange(
                        uriStock,
                        HttpMethod.GET,
                        requestEntityStock,
                        StockCompleteDto.class);
                String stock_name = responseStock.getBody().getStock_name();
                String stock_symbol = responseStock.getBody().getStock_symbol();
                int status = 1;
                int volume = random.nextInt(1, 11);
                long id_user = userRepository.randomBot();
                int type = random.nextInt(0, 2);

                LocalDateTime now = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(now);
                if (type == 1) {
                    BigDecimal price = BigDecimal.valueOf(random.nextDouble(12, 15.5));
                    userRepository.getById(id_user)
                            .setDollar_balance(price.multiply(BigDecimal.valueOf(volume)).add(BigDecimal.valueOf(10)));
                    userRepository.save(userRepository.getById(id_user));
                    OrderCreateDto orderCreateDto = new OrderCreateDto(id_user, id_stock, price, volume, status,
                            type, stock_name, stock_symbol);
                    System.out.println("montando o body para criar a ordem de compra type == 1");
                    System.out.println(orderCreateDto.toString());

                    try {
                        RestTemplate restTemplateOrder = new RestTemplate();
                        URI uriOrder;
                        uriOrder = new URI("http://localhost:8088/users_order");
                        HttpHeaders headersOrder = new HttpHeaders();
                        headersOrder.set("Authorization", token);
                        headersOrder.set("Content-Type", "application/json");

                        // (instancia,cabecalho)
                        HttpEntity requestEntityOrder = new HttpEntity(orderCreateDto, headersOrder);

                        // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
                        ResponseEntity<OrderCreateDto> responseOrder = restTemplateOrder.exchange(
                                uriOrder,
                                HttpMethod.POST,
                                requestEntityOrder,
                                OrderCreateDto.class);

                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                } else {
                    BigDecimal price = BigDecimal.valueOf(random.nextDouble(16, 20));


                    if (user_stock_balanceRepository.findByUserStock(id_user, id_stock) == null) {
                        user_stock_balanceRepository.createStockBalance(id_stock, id_user, timestamp, stock_name,
                                stock_symbol, timestamp, volume);
                    } else {
                        user_stock_balanceRepository.findByUserStock(id_user, id_stock).setVolume(volume);
                        user_stock_balanceRepository.save(user_stock_balanceRepository.findByUserStock(id_user, id_stock));
                    }

                    OrderCreateDto orderCreateDto = new OrderCreateDto(id_user, id_stock, price,
                            volume, status,
                            type, stock_name, stock_symbol);

                    // System.out.println("montando o body para criar a ordem de venda type == 0");
                    // System.out.println(orderCreateDto.toString());
                    try {
                        RestTemplate restTemplateOrder = new RestTemplate();
                        URI uriOrder;
                        uriOrder = new URI("http://localhost:8088/users_order");
                        HttpHeaders headersOrder = new HttpHeaders();
                        headersOrder.set("Authorization", token);
                        headersOrder.set("Content-Type", "application/json");

                        // (instancia,cabecalho)
                        HttpEntity requestEntityOrder = new HttpEntity(orderCreateDto, headersOrder);

                        // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
                        ResponseEntity<OrderCreateDto> responseOrder = restTemplateOrder.exchange(
                                uriOrder,
                                HttpMethod.POST,
                                requestEntityOrder,
                                OrderCreateDto.class);

                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }

}
