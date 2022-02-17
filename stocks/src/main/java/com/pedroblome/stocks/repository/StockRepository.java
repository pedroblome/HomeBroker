package com.pedroblome.stocks.repository;

import java.util.List;
import java.util.Optional;

import com.pedroblome.stocks.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findAllById(Long id);

    

}
