package com.pedroblome.user.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import com.pedroblome.user.model.User;
import com.pedroblome.user.model.User_stock_balance;
import com.pedroblome.user.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

   
    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    
    }
    @GetMapping(value = "/{id}")
    public Optional<User> searchStock(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        User useradd = userRepository.save(user);
        return new ResponseEntity<User>(useradd,HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        userRepository.deleteById(id);
        new ResponseEntity<>("user Deleted", HttpStatus.OK);
     
    }
  
   
}