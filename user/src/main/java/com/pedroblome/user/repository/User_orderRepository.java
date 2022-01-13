package com.pedroblome.user.repository;

import com.pedroblome.user.model.User_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_orderRepository extends JpaRepository<User_order, Long> {

}
