package com.pedroblome.user.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.pedroblome.user.Aux.ask;
import com.pedroblome.user.Aux.bid;
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

  private Object put;

  // verifica usuario

  public ResponseEntity<?> addOrder(User_order user_order, String token) {
    Stockdto stockdto = new Stockdto(user_order.getId(), user_order.getStock_name(), user_order.getStock_symbol());
    if (checkUser(user_order)) {
      if (checkStock(stockdto, token)) {
        user_orderRepository.save(user_order);
        // user_stock_balanceRepository.volume(user_order.getId_user(),user_order.getId_stock()).setVolume();

        try {
          var bid = new bid();
          var ask = new ask();
    
          RestTemplate template = new RestTemplate();
          URI uri;
          uri = new URI("http://localhost:8089/stocks" + user_order.getId_stock());
          HttpHeaders headers = new HttpHeaders();
          headers.set("Authorization", token);
          headers.set("Content-Type", "application/json");
    
          Map<String, Long> param = new HashMap<String, Long>();
          param.put("id", user_order.getId_stock());
    
          JSONObject jsonObject = new JSONObject();
    
          jsonObject.put("id", user_order.getId_stock());
          jsonObject.put("ask_min", ask.AskMin(user_order.getPrice(),
              user_order.getId_stock(), user_orderRepository));
          jsonObject.put("ask_max", ask.AskMax(user_order.getPrice(),
              user_order.getId_stock(), user_orderRepository));
          jsonObject.put("bid_min", bid.BidMin(user_order.getPrice(),
              user_order.getId_stock(), user_orderRepository));
          jsonObject.put("bid_max", bid.BidMax(user_order.getPrice(),
              user_order.getId_stock(), user_orderRepository));
    
          HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(),
              headers);
          System.out.println(jsonObject.toString());
          HttpEntity<String> response = template.exchange(uri.toString(),
              HttpMethod.PUT, request, String.class, param);
    
        }
    
        catch (URISyntaxException e) {
          e.printStackTrace();
        }

      }

    }

    // put de bid e ask

    return ResponseEntity.ok().body(user_order);

  }

  public boolean checkUser(@RequestBody User_order user_order) {
    int volume = user_stock_balanceRepository.volume(user_order.getId_user(), user_order.getId_stock()).getVolume();

    if (userRepository.existsById(user_order.getId_user())) {
      if (user_order.getType() == 1) {
        return true;
      }
      if (user_order.getType() == 0 && volume >= user_order.getVolume()) {
        return true;

      }
      return false;

    }

    // new ResponseStatusException(HttpStatus.BAD_REQUEST,
    // "type of order is invalid!!");
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
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add("Authorization", token);

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

      e.printStackTrace();
    }

    return true;
  }
}
