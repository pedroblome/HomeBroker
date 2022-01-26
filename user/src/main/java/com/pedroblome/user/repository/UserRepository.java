package com.pedroblome.user.repository;

import java.util.List;

import com.pedroblome.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    

}
