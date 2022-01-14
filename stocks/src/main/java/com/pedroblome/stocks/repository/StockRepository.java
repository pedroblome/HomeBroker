package com.pedroblome.stocks.repository;

import com.pedroblome.stocks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository <Stock, Long>{
}
