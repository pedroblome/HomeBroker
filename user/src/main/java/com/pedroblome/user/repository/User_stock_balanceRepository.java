package com.pedroblome.user.repository;

import com.pedroblome.user.model.User_stock_ballance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_stock_balanceRepository extends JpaRepository<User_stock_ballance, Long> {

}

