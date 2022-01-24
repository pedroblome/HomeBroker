package com.pedroblome.user.service;

import java.util.List;
import java.util.Optional;

import com.pedroblome.user.model.User;
import com.pedroblome.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean user(@RequestBody User user) {
        if (userRepository.existsById(user.getId())) {
            return true;
        } else {
            return false;
        }
    }

}
