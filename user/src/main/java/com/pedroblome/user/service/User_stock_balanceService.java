package com.pedroblome.user.service;

import java.util.Optional;

import com.pedroblome.user.model.User_stock_ballance;
import com.pedroblome.user.repository.User_stock_balanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class User_stock_balanceService {
    @Autowired
    public User_stock_balanceRepository user_stock_balanceRepository;
    //caçar stocks por id
}