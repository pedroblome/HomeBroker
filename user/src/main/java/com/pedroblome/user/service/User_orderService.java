package com.pedroblome.user.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.pedroblome.user.controller.dto.StockAskBidDto;
import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.model.User_stock_balance;
import com.pedroblome.user.repository.UserRepository;
import com.pedroblome.user.repository.User_orderRepository;
import com.pedroblome.user.repository.User_stock_balanceRepository;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class User_orderService {


  @Autowired
  private UserRepository userRepository;

  @Autowired
  private User_stock_balanceRepository user_stock_balanceRepository;

  @Autowired
  private User_orderRepository user_orderRepository;

  // verifica usuario

  public ResponseEntity<?> addOrder(User_order user_order, String token) {
    Stockdto stockdto = new Stockdto(user_order.getId_stock(),
        user_order.getStock_name(),
        user_order.getStock_symbol());

    if (user_order.getType() == 1 || user_order.getType() == 0) {
      if (user_order.getStatus() == 1) {
        if (checkUser(user_order)) {
          if (checkStock(stockdto, token)) {
            user_order.setRemaing_volume(user_order.getVolume());
            if (user_order.getType() == 0) {
              int volumeUpdate = user_stock_balanceRepository
                  .findByUserStock(user_order.getId_user(), user_order.getId_stock())
                  .getVolume() - user_order.getVolume();
              user_stock_balanceRepository.findByUserStock(user_order.getId_user(), user_order.getId_stock())
                  .setVolume(volumeUpdate);
            }

            // criando o dto e a conexao
            try {
              BigDecimal askmax = user_orderRepository.askMax(user_order.getId_stock());
              BigDecimal askmin = user_orderRepository.askMin(user_order.getId_stock());
              BigDecimal bidmax = user_orderRepository.bidMax(user_order.getId_stock());
              BigDecimal bidmin = user_orderRepository.bidMin(user_order.getId_stock());
              StockAskBidDto updateStock = new StockAskBidDto(user_order.getId_stock(), askmin, askmax, bidmin, bidmax,
                  user_order.getUpdated_on());
              System.out.println("---------------------------------");
              System.out.println(updateStock);
              RestTemplate restTemplate = new RestTemplate();
              URI uri;
              uri = new URI("http://localhost:8089/stocks/askbid/" + user_order.getId_stock());
              HttpHeaders headers = new HttpHeaders();
              headers.set("Authorization", token);
              headers.set("Content-Type", "application/json");

              // (instancia,cabecalho)
              HttpEntity requestEntity = new HttpEntity(updateStock, headers);

              // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
              ResponseEntity<StockAskBidDto> response = restTemplate.exchange(
                  uri,
                  HttpMethod.PUT,
                  requestEntity,
                  StockAskBidDto.class);

            } catch (URISyntaxException e) {
              System.out.println("deu erro");
              e.printStackTrace();
            }

            //
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            // .
            User_order orderSave = user_orderRepository.save(user_order);
            return ResponseEntity.ok().body(orderSave);
          }
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
              "stock_name or stock_symbol doesnt match with given id_stock!!");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Inexistent id or insuficient balance or insuficient volume for stockSell!!");

      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Cannot create order with status diferent of 1!!");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
        "Cannot create order with type diferent of 1 or 0!!");
  }

  public boolean checkUser(@RequestBody User_order user_order) {

    if (userRepository.existsById(user_order.getId_user())) {
      if (user_order.getType() == 1) {
        return true;
      }
      if (user_order.getType() == 0) {
        try {
          Integer volume = user_stock_balanceRepository.volume(user_order.getId_user(), user_order.getId_stock())
              .getVolume();
          if (volume != null && volume >= user_order.getVolume()) {
            return true;
          }
          return false;

        } catch (NullPointerException e) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
              "User doesnt have enoug of this stock to sell!");
        }
      }

    }
    return false;
  }

  public Boolean checkStock(Stockdto stockdto, String token) {
    try {
      // URL DESTINO
      RestTemplate restTemplate = new RestTemplate();
      URI uri;
      uri = new URI("http://localhost:8089/stocks/dto/" + stockdto.getId());

      // CABEÇALHO
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");
      headers.set("Authorization", token);

      // (instancia,cabecalho)
      HttpEntity requestEntity = new HttpEntity(stockdto, headers);

      // HttpMethod.PUT , HttpMethod.POST , HttpMethod.GET
      ResponseEntity<Boolean> response = restTemplate.exchange(
          uri,
          HttpMethod.POST,
          requestEntity,
          Boolean.class);
      return response.getBody();
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;

  }

}