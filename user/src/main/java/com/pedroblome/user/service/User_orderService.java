package com.pedroblome.user.service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User;
//import com.pedroblome.user.controller.dto.Stockdto;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.model.User_stock_balance;
import com.pedroblome.user.repository.UserRepository;
import com.pedroblome.user.repository.User_stock_balanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
//import reactor.core.publisher.Mono;

@Service
public class User_orderService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private User_stock_balanceRepository user_stock_balanceRepository;

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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false;
  }

  // update dollar ballance
  // public void updateUserBalance(@RequestBody User_order user_order) {
  // BigDecimal total =
  // user_order.getPrice().multiply(BigDecimal.valueOf((user_order.getVolume())));
  // BigDecimal userBallance =
  // userRepository.getById(user_order.getId_user()).getDollar_balance();
  // BigDecimal updateDollar = userBallance.subtract(total);

  // Stockdto stockdto = new Stockdto(user_order.getId_stock(),
  // user_order.getStock_name(),
  // user_order.getStock_symbol());

  // if (checkUser(user_order) == true && checkStock(stockdto) == true) {
  // if (user_order.getType() == 1) {
  // User userBalance = userRepository.getById(user_order.getId_user());
  // userBalance.setDollar_balance(updateDollar);
  // userRepository.save(userBalance);

  // }
  // if (user_order.getType() == 0) {

  // }

  // }

  // }

  // update stock ballance

}
