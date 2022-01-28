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
  public boolean checkUser(@RequestBody User_order user_order) {

    BigDecimal total = user_order.getPrice().multiply(BigDecimal.valueOf((user_order.getVolume())));
    BigDecimal userBallance = userRepository.getById(user_order.getId_user()).getDollar_balance();

    if (userRepository.existsById(user_order.getId_user())) {
      if (user_order.getType() == 1) {
        return true;
      }
      if (user_order.getType() == 0) {
        User_stock_balance stock = user_stock_balanceRepository.volume(user_order.getId_user(),
            user_order.getId_stock());
        if (stock.getVolume() >= user_order.getVolume()) {

          User_stock_balance updateStock = user_stock_balanceRepository.findByUserStock(user_order.getId_user(),
              user_order.getId_stock());
          updateStock.setVolume(stock.getVolume() - user_order.getVolume());
          user_stock_balanceRepository.save(updateStock);

          return true;
        }
        return false;

      }
    }
    return false;
  }

  public Boolean checkStock(Stockdto stockdto) {
    try {
      // URL DESTINO
      RestTemplate restTemplate = new RestTemplate();
      URI uri;
      uri = new URI("http://localhost:8089/stocks/dto/" + stockdto.getId());

      // CABEÇALHO
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");
      // headers.set("atuhorzition, "token")

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

    return false;
  }

  public ResponseEntity<?> addOrder(User_order user_order) {

    Stockdto stockdto = new Stockdto(user_order.getId_stock(),
        user_order.getStock_name(),
        user_order.getStock_symbol());
    int a = 2;
    if (a == 2) {
      if (checkStock(stockdto)) {
        if (checkUser(user_order)) {
          if (user_order.getRemaing_volume() == null) {
            user_order.setRemaing_volume(user_order.getVolume());
          }
          user_orderRepository.save(user_order);

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Inexistent id or insuficient balance or insuficient volume for stockSell");

      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "stock_name or stock_symbol doesnt match with given id_stock  ");
    }

    // put de bid e ask
    var bid = new bid();
    var ask = new ask();

    RestTemplate template = new RestTemplate();
    URI uri;
    try {
      uri = new URI("http://localhost:8089/stocks/dto/" + user_order.getId_stock());
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");

      Map<String, Long> param = new HashMap<String, Long>();
      param.put("id", user_order.getId_stock());

      JSONObject jsonObject = new JSONObject();
      
      
      jsonObject.put("id", user_order.getId_stock());
      jsonObject.put("ask_min", ask.AskMin(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));
      jsonObject.put("ask_max", ask.AskMax(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));
      jsonObject.put("bid_min", bid.BidMin(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));
      jsonObject.put("bid_max", bid.BidMax(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));

      HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
      System.out.println(jsonObject.toString());
      HttpEntity<String> response = template.exchange(uri.toString(), HttpMethod.PUT, request, String.class, param);

    }

    catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok().body(user_order);

  }

}

//