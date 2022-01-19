package com.pedroblome.user.controller;

import java.util.List;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.pedroblome.user.Aux.ask;
import com.pedroblome.user.Aux.bid;
import com.pedroblome.user.model.User_order;
import com.pedroblome.user.repository.User_orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;



@RestController
@RequestMapping("/users_order")
public class User_orderController {

    @Autowired
    private User_orderRepository user_orderRepository;

    @GetMapping
    public List<User_order> list() {
        return user_orderRepository.findAll();
    }

    //adding a new User_order to the database
    @PostMapping
    public User_order add(@RequestHeader("Authorization") String token, @RequestBody User_order user_order) {
        var bid = new bid();
        var ask = new ask();


        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8088")
                .path("stock/{id}")
                .build();

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        Map<String, Long> param = new HashMap<String, Long>();
        param.put("id", user_order.getId_stock());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user_order.getId_stock());
        jsonObject.put("ask_min", ask.AskMin(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));
        jsonObject.put("ask_max", ask.AskMax(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));
        jsonObject.put("bid_min", bid.BidMin(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));
        jsonObject.put("bid_max", bid.BidMax(user_order.getPrice(), user_order.getId_stock(), user_orderRepository));

        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);

        HttpEntity<String> response = template.exchange(uri.toString(), HttpMethod.PUT, request, String.class, param);

        return user_orderRepository.save(user_order);
    }


}
