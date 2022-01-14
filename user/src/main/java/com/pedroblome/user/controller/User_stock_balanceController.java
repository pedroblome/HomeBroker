// package com.pedroblome.user.controller;

// import java.util.List;

// import com.pedroblome.user.model.User_stock_balance;
// import com.pedroblome.user.repository.User_stock_balanceRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/user_stock_balance")
// public class User_stock_balanceController {

//     @Autowired
//     private User_stock_balanceRepository user_stock_balanceRepository;

//     @GetMapping
//     public List<User_stock_balance> list(){
//         return user_stock_balanceRepository.findAll();
//     }

//     @PostMapping
//     public User_stock_balance add(@RequestBody User_stock_balance user_stock_balance){
//         return user_stock_balanceRepository.save(user_stock_balance);
//     }
    
// }
