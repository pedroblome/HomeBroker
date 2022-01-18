package com.pedroblome.user.controller;

import java.util.List;
import java.util.Optional;
import com.pedroblome.user.model.User_stock_ballance;
import com.pedroblome.user.repository.User_stock_balanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_stock_balance")
public class User_stock_balanceController {

    @Autowired
    private User_stock_balanceRepository user_stock_balanceRepository;

    @GetMapping
    public List<User_stock_ballance> list() {
        return user_stock_balanceRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User_stock_ballance> searchStock(@PathVariable Long id) {
        return user_stock_balanceRepository.findById(id);
    }

    @PostMapping
    public User_stock_ballance add(@RequestBody User_stock_ballance stock_ballance) {
        return user_stock_balanceRepository.save(stock_ballance);

    }
    

}
