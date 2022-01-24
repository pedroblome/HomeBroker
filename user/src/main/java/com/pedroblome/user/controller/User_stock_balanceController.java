package com.pedroblome.user.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.pedroblome.user.model.User_stock_ballance;
import com.pedroblome.user.model.User_stock_ballancePK;
import com.pedroblome.user.repository.User_stock_balanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user_stock_balance")
public class User_stock_balanceController {

    @Autowired
    private User_stock_balanceRepository user_stock_balanceRepository;

    @GetMapping
    public List<User_stock_ballance> list() {
        return user_stock_balanceRepository.findAll();
    }

    @GetMapping("/user/{id_user}")

    public Optional<User_stock_ballance> searchByUser(@RequestBody User_stock_ballance user_stock_ballance) {
        if (user_stock_balanceRepository.existsById(user_stock_ballance.getId_user())) {
            return user_stock_balanceRepository.findById(user_stock_ballance.getId_user());

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Id of user not exists: ", user_stock_ballance.getId_user()));

        }
    }

   
    @PostMapping
    public User_stock_ballance add(@RequestBody User_stock_ballance stock_ballance) {
        return user_stock_balanceRepository.save(stock_ballance);

    }

    public ObjectMapper configureDeserializerDate() {
        // THIS FUNCTION CONFIGURE DESERIALIZER TO GET THE CORRECT DATE FROM RESPONSE
        // AND RETURN DE CORRECT OBJECT

        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));

        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

}
