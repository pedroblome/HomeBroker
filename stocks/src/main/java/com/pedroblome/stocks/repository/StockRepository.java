package com.pedroblome.stocks.repository;

import java.util.List;

import com.pedroblome.stocks.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllById(Long id);

    

}
