package com.pedroblome.user.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import javax.websocket.server.PathParam;

import com.pedroblome.user.model.User;
import com.pedroblome.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // method to list all users in database
    @GetMapping 
    public List<User> list() {
        return userRepository.findAll();
    }

    // method to add a new user into the database
    @PostMapping
    public User add(@RequestBody User user) {
        return userRepository.save(user);
    }


  
    

}